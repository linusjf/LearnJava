package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class OReillyByName {
  private OReillyByName() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      InetAddress[] addresses =
          InetAddress.getAllByName("www.oreilly.com");
      for (InetAddress address: addresses)
        System.out.println(address);
    } catch (UnknownHostException ex) {
      System.err.println("Could not find www.oreilly.com"
                         + ex.getMessage());
    }
  }
}
