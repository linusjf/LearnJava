package networking;

import java.net.InetAddress;

public final class AddressTests {

  private AddressTests() {
    throw new IllegalStateException("Private constructor");
  }

  public static int getVersion(InetAddress ia) {
    byte[] address = ia.getAddress();
    if (address.length == 4) return 4;
    else if (address.length == 16) return 6;
    else return -1;
  }
}
