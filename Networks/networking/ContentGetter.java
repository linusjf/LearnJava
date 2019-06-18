package networking;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public final class ContentGetter {
  private ContentGetter() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    if (args.length > 0) {
      // Open the URLs for reading
      for (String arg : args) {
        try {
          processUrl(arg);
        } catch (MalformedURLException ex) {
          System.err.println(args[0] + " is not a parseable URL");
        } catch (IOException ex) {
          System.err.println(ex);
        }
      }
    }
  }

  private static void processUrl(String url) throws MalformedURLException, IOException {
    URL u = new URL(url);
    Object o = u.getContent();
    System.out.println("I got a " + o.getClass().getName() + " for " + url);
  }
}
