package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public enum DaytimeUDPClient {
  ;
  private static final int PORT = 13;

  // clang-format off
  private static final String[] HOSTNAMES = new String[] {
    "time.nist.gov",
    "a-g.nist.gov",
    "time-b-g.nist.gov",
    "time-c-g.nist.gov",
    "time-d-g.nist.gov",
    "time-d-g.nist.gov",
    "time-e-g.nist.gov",
    "time-e-g.nist.gov",
    "time-a-wwv.nist.gov",
    "time-b-wwv.nist.gov",
    "time-c-wwv.nist.gov",
    "time-d-wwv.nist.gov",
    "time-d-wwv.nist.gov",
    "time-e-wwv.nist.gov",
    "time-e-wwv.nist.gov",
    "time-a-b.nist.gov",
    "time-b-b.nist.gov",
    "time-c-b.nist.gov",
    "time-d-b.nist.gov",
    "time-d-b.nist.gov",
    "time-e-b.nist.gov",
    "time-e-b.nist.gov",
    "utcnist.colorado.edu",
    "utcnist2.colorado.ed",
    "tick.usno.navy.mil",
    "tock.usno.navy.mil",
    "ntp2.usno.navy.mil",
    "tick.usnogps.navy.mil",
    "tock.usnogps.navy.mil",
  };

  // clang-format on
  public static void main(String[] args) {
    try (DatagramSocket socket = new DatagramSocket(0)) {
      socket.setSoTimeout(10_000);
      if (args.length == 0) {
        Random random = new Random();
        int index = random.nextInt(HOSTNAMES.length);
        String host = HOSTNAMES[index];
        connectToTimeServer(socket, host, PORT);
      } else {
        String hostname = args[0];
        int port;
        try {
          port = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
          System.err.println(ex);
          port = PORT;
        }
        connectToTimeServer(socket, hostname, port);
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private static void connectToTimeServer(
    DatagramSocket socket,
    String hostname,
    int port
  )
    throws IOException {
    InetAddress host = InetAddress.getByName(hostname);
    DatagramPacket request = new DatagramPacket(new byte[1], 1, host, port);
    DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
    socket.send(request);
    socket.receive(response);
    String result = new String(
      response.getData(),
      0,
      response.getLength(),
      "US-ASCII"
    );
    System.out.println(result);
  }
}
