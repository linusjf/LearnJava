package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SuppressWarnings("PMD.AvoidUsingHardCodedIP")
public final class ReverseTest {
  private ReverseTest() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      InetAddress ia = InetAddress.getByName("208.201.239.100");
      System.out.println(ia.getHostAddress() + " : "
                         + ia.getCanonicalHostName());
      ia = InetAddress.getByName("23.199.87.25");
      System.out.println(ia.getHostAddress() + " : "
                         + ia.getCanonicalHostName());
      ia = InetAddress.getByName("104.69.171.15");
      System.out.println(ia.getHostAddress() + " : "
                         + ia.getCanonicalHostName());
    } catch (UnknownHostException e) {
      System.err.println("No reverse lookup possible: " + e.getMessage());
    }
  }
}
