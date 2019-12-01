package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public enum UDPTimeClient {
  ;
  public static final int PORT = 37;
  public static final String DEFAULT_HOST = "time.nist.gov";
  public static final int NO_OF_BYTES_EXPECTED = 4;

  private static InetAddress getAddress(String... args) {
    try {
      if (args.length > 0)
        return InetAddress.getByName(args[0]);
      else
        return InetAddress.getByName(DEFAULT_HOST);
    } catch (UnknownHostException ex) {
      throw new AssertionError("Usage: java UDPTimeClient [host]", ex);
    }
  }

  @SuppressWarnings("checkstyle:returncount")
  public static void main(String[] args) {
    InetAddress host = getAddress(args);
    UDPPoke poker = new UDPPoke(host, PORT);
    byte[] response = poker.poke();
    if (response == null) {
      System.out.println("No response within allotted time");
      return;
    } else if (response.length != NO_OF_BYTES_EXPECTED) {
      System.out.println("Unrecognized response format");
      return;
    }

    // The time protocol sets the epoch at 1900,
    // the Java Date class at 1970. This number
    // converts between them.
    long differenceBetweenEpochs = 2_208_988_800L;
    long secondsSince1900 = 0;
    for (int i = 0; i < 4; i++) {
      secondsSince1900 = (secondsSince1900 << 8) | (response[i] & 0x000000FF);
    }
    long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
    long msSince1970 = secondsSince1970 * 1000;
    Date time = new Date(msSince1970);
    System.out.println(time);
  }
}
