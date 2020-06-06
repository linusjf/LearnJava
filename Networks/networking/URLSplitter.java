package networking;

import java.net.MalformedURLException;
import java.net.URL;

public final class URLSplitter {
  private URLSplitter() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    for (String arg: args) {
      try {
        URL u = new URL(arg);
        System.out.println("The URL is " + u);
        System.out.println("The scheme is " + u.getProtocol());
        System.out.println("The user info is " + u.getUserInfo());
        String host = u.getHost();
        if (host != null) {
          int atSign = host.indexOf('@');
          if (atSign != -1)
            host = host.substring(atSign + 1);
        }
        System.out.println("The host is " + host);
        System.out.println("The port is " + u.getPort());
        System.out.println("The path is " + u.getPath());
        System.out.println("The ref is " + u.getRef());
        System.out.println("The query string is " + u.getQuery());
      } catch (MalformedURLException ex) {
        System.err.println(arg + " is not a URL I understand.");
      }
      System.out.println();
    }
  }
}
