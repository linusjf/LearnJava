package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IBiblioAliases {
  public static void main(String args[]) {
    try {
      InetAddress ibiblio = InetAddress.getByName("www.ibiblio.org");
      InetAddress helios = InetAddress.getByName("ibiblio.org");
      if (ibiblio.equals(helios)) {
        System.out.println("www.ibiblio.org is the same as ibiblio.org");
      } else {
        System.out.println("www.ibiblio.org is not the same as ibiblio.org");
      }
    } catch (UnknownHostException ex) {
      System.out.println("Host lookup failed.");
    }
  }
}
