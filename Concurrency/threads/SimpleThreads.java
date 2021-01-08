package threads;

import java.util.concurrent.TimeUnit;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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
@SuppressWarnings("PMD.SystemPrintln")
public enum SimpleThreads {
  ;
  // Display a message, preceded by
  // the name of the current thread
  @SuppressWarnings("PMD.LawOfDemeter")
  static void threadMessage(String message) {
    String threadName = Thread.currentThread().getName();
    System.out.format("%s: %s%n", threadName, message);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static long getPatience(String... args) {
    if (args.length > 0) {
      try {
        return Long.parseLong(args[0]) * 1000L;
      } catch (NumberFormatException e) {
        System.err.println("Argument must be an integer.");
      }
    }
    return 1000L * 60L * 60L;
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String... args) {
    // Delay, in milliseconds before
    // we interrupt MessageLoop
    // thread (default one hour).
    final long patience = getPatience(args);

    // If command line argument
    // present, gives patience
    // in seconds.
    try {
      threadMessage("Starting MessageLoop thread");
      Thread t = new Thread(new MessageLoop());
      final long startTime = System.currentTimeMillis();
      t.start();

      threadMessage("Waiting for MessageLoop thread to finish");

      // loop until MessageLoop
      // thread exits
      while (t.isAlive()) {
        threadMessage("Still waiting...");

        // Wait maximum of 1 second
        // for MessageLoop thread
        // to finish.
        t.join(1000);
        if ((System.currentTimeMillis() - startTime) > patience
            && t.isAlive()) {
          threadMessage("Tired of waiting!");
          t.interrupt();

          // Shouldn't be long now
          // -- wait indefinitely
          t.join();
        }
      }
      threadMessage("Finally!");
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  private static class MessageLoop implements Runnable {
    String[] importantInfo = {"Mares eat oats",
                              "Does eat oats",
                              "Little lambs eat ivy",
                              "A kid will eat ivy too"};

    @Override
    public void run() {
      try {
        for (String info: importantInfo) {
          // Pause for 4 seconds
          TimeUnit.MILLISECONDS.sleep(4000);

          // Print a message
          threadMessage(info);
        }
      } catch (InterruptedException e) {
        threadMessage("I wasn't done!");
      }
    }
  }
}
