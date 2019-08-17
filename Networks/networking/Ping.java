package networking;
/*
 * Copyright (c) 2001, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/* Connect to each of a list of hosts and measure the time required to complete
 * the connection.  This example uses a selector and two additional threads in
 * order to demonstrate non-blocking connects and the multithreaded use of a
 * selector.
 */
@SuppressWarnings("PMD.ShortClassName")
public final class Ping {
  // The default daytime port
  static final int DAYTIME_PORT = 13;

  // The port we'll actually use
  static int port = DAYTIME_PORT;

  private Ping() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Usage: java Ping [port] host...");
      return;
    }
    int firstArg = 0;

    try {
      // If the first argument is a string of digits then we take that
      // to be the port number to use
      if (Pattern.matches("[0-9]+", args[0])) {
        port = Integer.parseInt(args[0]);
        firstArg = 1;
      }

      // Create the threads and start them up
      Printer printer = new Printer();
      printer.start();
      Connector connector = new Connector(printer);
      connector.start();

      // Create the targets and add them to the connector
      List<Target> targets = new LinkedList<>();
      for (int i = firstArg; i < args.length; i++) {
        Target t = new Target(args[i]);
        targets.add(t);
        connector.add(t);
      }

      // Wait for everything to finish
      Thread.sleep(2000);
      connector.shutDown();
      System.err.println("shutting down....");
      connector.join();

      // Print status of targets that have not yet been shown
      for (Target t : targets) {
        // Target t = (Target)i.next();
        if (!t.shown)
          t.show();
      }
    } catch (IOException | InterruptedException ex) {
      System.err.println(ex);
    }
  }

  // Representation of a ping target
  //
  static class Target {
    InetSocketAddress address;
    SocketChannel channel;
    Exception failure;
    long connectStart;
    long connectFinish;
    boolean shown;

    Target(String host) {
      try {
        address = new InetSocketAddress(InetAddress.getByName(host), port);
      } catch (IOException x) {
        failure = x;
      }
    }

    void show() {
      String result = "Timed out";
      if (connectFinish > 0)
        result = Long.toString(connectFinish - connectStart) + "ms";
      if (failure != null)
        result = failure.toString();
      System.out.println(address + " : " + result);
      shown = true;
    }
  }

  // Thread for printing targets as they're heard from
  //
  static class Printer extends Thread {
    List<Target> pending = new LinkedList<>();

    Printer() {
      super();
      setName("Printer");
      setDaemon(true);
    }

    void add(Target t) {
      synchronized (pending) {
        pending.add(t);
        pending.notifyAll();
      }
    }

    @Override
    public void run() {
      try {
        for (;;) {
          Target t = null;
          synchronized (pending) {
            while (pending.isEmpty()) pending.wait();
            t = (Target) pending.remove(0);
          }
          t.show();
        }
      } catch (InterruptedException x) {
        return;
      }
    }
  }

  // Thread for connecting to all targets in parallel via a single selector
  //
  @SuppressWarnings("PMD.AvoidUsingVolatile")
  static class Connector extends Thread {
    Selector sel;
    Printer printer;
    volatile boolean isShutdown;

    // List of pending targets.  We use this list because if we try to
    // register a channel with the selector while the connector thread is
    // blocked in the selector then we will block.
    //
    List<Target> pending = new LinkedList<>();

    Connector(Printer pr) throws IOException {
      super();
      printer = pr;
      sel = Selector.open();
      setName("Connector");
    }

    // Initiate a connection sequence to the given target and add the
    // target to the pending-target list
    //
    void add(Target t) {
      SocketChannel sc = null;
      try {
        // Open the channel, set it to non-blocking, initiate connect
        sc = SocketChannel.open();
        sc.configureBlocking(false);
        if (t.address != null) {
          boolean connected = sc.connect(t.address);

          // Record the time we started
          t.channel = sc;
          t.connectStart = System.currentTimeMillis();

          if (connected) {
            t.connectFinish = t.connectStart;
            sc.close();
            printer.add(t);
          } else {
            // Add the new channel to the pending list
            synchronized (pending) {
              pending.add(t);
            }

            // Nudge the selector so that it will process the pending list
            sel.wakeup();
          }
        }
      } catch (IOException x) {
        if (sc != null) {
          try {
            sc.close();
          } catch (IOException xx) {
            System.err.println(xx);
          }
        }
        t.failure = x;
        printer.add(t);
      }
    }

    // Process any targets in the pending list
    //
    void processPendingTargets() throws IOException {
      synchronized (pending) {
        while (!pending.isEmpty()) {
          Target t = (Target) pending.remove(0);
          try {
            // Register the channel with the selector, indicating
            // interest in connection completion and attaching the
            // target object so that we can get the target back
            // after the key is added to the selector's
            // selected-key set
            t.channel.register(sel, SelectionKey.OP_CONNECT, t);

          } catch (IOException x) {
            // Something went wrong, so close the channel and
            // record the failure
            t.channel.close();
            t.failure = x;
            printer.add(t);
          }
        }
      }
    }

    // Process keys that have become selected
    //
    void processSelectedKeys() throws IOException {
      for (Iterator i = sel.selectedKeys().iterator(); i.hasNext();) {
        // Retrieve the next key and remove it from the set
        SelectionKey sk = (SelectionKey) i.next();
        i.remove();

        // Retrieve the target and the channel
        Target t = (Target) sk.attachment();
        SocketChannel sc = (SocketChannel) sk.channel();

        // Attempt to complete the connection sequence
        try {
          if (sc.finishConnect()) {
            sk.cancel();
            t.connectFinish = System.currentTimeMillis();
            sc.close();
            printer.add(t);
          }
        } catch (IOException x) {
          sc.close();
          t.failure = x;
          printer.add(t);
        }
      }
    }

    // Invoked by the main thread when it's time to shut down
    //
    void shutDown() {
      isShutdown = true;
      sel.wakeup();
    }

    // Connector loop
    //
    @Override
    public void run() {
      for (;;) {
        try {
          int n = sel.select();
          if (n > 0)
            processSelectedKeys();
          processPendingTargets();
          if (isShutdown) {
            sel.close();
            return;
          }
        } catch (IOException x) {
          System.err.println(x);
        }
      }
    }
  }
}
