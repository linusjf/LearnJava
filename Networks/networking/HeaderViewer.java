package networking;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public final class HeaderViewer {
  private HeaderViewer() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    for (String arg: args) {
      try {
        URL u = new URL(arg);
        URLConnection uc = u.openConnection();
        printHeaders(uc);
      } catch (MalformedURLException ex) {
        System.err.println(arg + " is not a URL I understand");
      } catch (IOException ex) {
        System.err.println(ex);
      }
      System.out.println();
    }
  }

  private static void printHeaders(URLConnection uc) throws IOException {
    System.out.println("Content-type: " + uc.getContentType());
    if (uc.getContentEncoding() != null)
      System.out.println("Content-encoding: " + uc.getContentEncoding());
    if (uc.getDate() != 0)
      System.out.println("Date: " + new Date(uc.getDate()));
    if (uc.getLastModified() != 0)
      System.out.println("Last modified: " + new Date(uc.getLastModified()));
    if (uc.getExpiration() != 0)
      System.out.println("Expiration date: " + new Date(uc.getExpiration()));
    if (uc.getContentLength() != -1)
      System.out.println("Content-length: " + uc.getContentLength());
  }
}
