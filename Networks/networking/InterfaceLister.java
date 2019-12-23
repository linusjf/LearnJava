package networking;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public final class InterfaceLister {
  private InterfaceLister() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      Enumeration<NetworkInterface> interfaces =
          NetworkInterface.getNetworkInterfaces();
      interfaces.asIterator().forEachRemaining(System.out::println);
    } catch (SocketException se) {
      System.err.println("Socket error: "
                         + se.getMessage());
    }
  }
}
