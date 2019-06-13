package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class MyAddress {

  private MyAddress() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      InetAddress address = InetAddress.getLocalHost();
      System.out.println(address);
      String dottedQuad = address.getHostAddress();
      System.out.println(dottedQuad);
    } catch (UnknownHostException ex) {
      System.out.println("Could not find this computer's address: " + ex.getMessage());
    }
  }
}
