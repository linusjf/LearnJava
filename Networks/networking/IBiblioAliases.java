package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class IBiblioAliases {
  private IBiblioAliases() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      InetAddress ibiblio = InetAddress.getByName("www.ibiblio.org");
      InetAddress helios = InetAddress.getByName("ibiblio.org");
      if (ibiblio.equals(helios)) {
        System.out.println("www.ibiblio.org is the same as ibiblio.org");
      } else {
        System.out.println("www.ibiblio.org is not the same as ibiblio.org");
      }
      InetAddress github = InetAddress.getByName("www.github.com");
      InetAddress blog = InetAddress.getByName("github.blog");
      if (github.equals(blog)) {
        System.out.println("www.github.com is the same as github.blog");
      } else {
        System.out.println("www.github.com is not the same as github.blog");
      }
    } catch (UnknownHostException ex) {
      System.out.println("Host lookup failed.");
    }
  }
}
