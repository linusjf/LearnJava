package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public enum MyLocalIPAddress {
  ;
  public static void main(String[] args) {
    try {
      InetAddress address = InetAddress.getLocalHost();
      System.out.println(address);
    } catch (UnknownHostException uhEx) {
      System.out.println("Could not find local address!");
    }
  }
}
