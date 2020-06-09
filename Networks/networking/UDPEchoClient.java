package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("PMD.AvoidUsingVolatile")
public enum UDPEchoClient {
  ;
  public static final int PORT = 7;

  private static String getHostName(String... args) {
    if (args.length > 0)
      return args[0];
    return "localhost";
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    if (args.length > 1) {
      try {
        return Integer.parseInt(args[1]);
      } catch (NumberFormatException nfe) {
        // empty catch block
      }
    }
    return PORT;
  }

  public static void main(String[] args) {
    String hostname = getHostName(args);

    int port = getPort(args);

    try {
      InetAddress ia = InetAddress.getByName(hostname);
      DatagramSocket socket = new DatagramSocket();
      SenderThread sender = new SenderThread(socket, ia, port);
      sender.start();
      ReceiverThread receiver = new ReceiverThread(socket);
      receiver.start();
      sender.join();
      TimeUnit.MILLISECONDS.sleep(1000);

      // halt will not stop blocking call receive
      receiver.halt();

      // explicitly call close to kill receiving thread
      socket.close();
      receiver.join();
    } catch (UnknownHostException | SocketException | InterruptedException ex) {
      System.err.println(ex);
    }
  }

  static class SenderThread extends Thread {
    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private final InetAddress server;
    private final DatagramSocket socket;
    private final int port;
    private volatile boolean stopped;

    SenderThread(DatagramSocket socket, InetAddress address, int port) {
      this.server = address;
      this.port = port;
      this.socket = socket;
      this.socket.connect(server, port);
    }

    public void halt() {
      this.stopped = true;
    }

    @SuppressWarnings({"checkstyle:returncount", "PMD.LawOfDemeter"})
    @Override
    public void run() {
      try (BufferedReader userInput =
               new BufferedReader(new InputStreamReader(System.in, UTF_8))) {
        while (true) {
          if (stopped)
            return;
          String theLine = userInput.readLine();
          if (".".equals(theLine))
            return;
          byte[] data = theLine.getBytes("UTF-8");
          DatagramPacket output =
              new DatagramPacket(data, data.length, server, port);
          socket.send(output);
          Thread.yield();
        }
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }

  static class ReceiverThread extends Thread {
    private final DatagramSocket socket;
    private volatile boolean stopped;

    ReceiverThread(DatagramSocket socket) {
      this.socket = socket;
    }

    public void halt() {
      this.stopped = true;
    }

    @Override
    public void run() {
      while (true) {
        if (stopped)
          return;
        DatagramPacket dp = new DatagramPacket(new byte[65_507], 65_507);
        try {
          socket.receive(dp);
          String s = new String(dp.getData(), 0, dp.getLength(), "UTF-8");
          System.out.println(s);
          Thread.yield();
        } catch (IOException ex) {
          System.err.println(ex);
        }
      }
    }
  }
}
