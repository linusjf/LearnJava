package networking;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public final class InterfaceLister {

  private InterfaceLister() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface ni = interfaces.nextElement();
        System.out.println(ni);
      }
    } catch (SocketException se) {
      System.err.println("Socket error: " + se.getMessage());
    }
  }
}
