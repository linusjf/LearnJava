package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/** * This example can no longer be used as-is. Usage is now only via the Google APIs ** */
public class GoogleSearch {
  public static void main(String[] args) {
    String target = "";
    for (int i = 0; i < args.length; i++) {
      target += args[i] + " ";
    }
    target = target.trim();
    QueryString query = new QueryString();
    query.add("q", target);
    query.add("newwindow", "1");
    query.add("safe", "active");
    query.add("ei", "_4oFXY3oOpL39QOSvLCoBw");
    query.add("source", "hp");
    try {
      URL u = new URL("https://www.google.com/search?" + query);
      try (InputStream in = new BufferedInputStream(u.openStream())) {
        InputStreamReader theHTML = new InputStreamReader(in);
        int c;
        while ((c = theHTML.read()) != -1) {
          System.out.print((char) c);
        }
      }
    } catch (MalformedURLException ex) {
      System.err.println("MalformedURL : " + ex);
    } catch (IOException ex) {
      System.err.println("IOException : " + ex);
    }
  }
}
