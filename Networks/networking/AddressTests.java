package networking;

import java.net.InetAddress;

public final class AddressTests {
  private static final int IPV4_LENGTH = 4;

  private static final int IPV6_LENGTH = 16;

  private AddressTests() {
    throw new IllegalStateException("Private constructor");
  }

  public static int getVersion(InetAddress ia) {
    byte[] address = ia.getAddress();
    if (address.length == IPV4_LENGTH) return 4;
    else if (address.length == IPV6_LENGTH) return 6;
    else return -1;
  }
}
