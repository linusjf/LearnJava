package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/** Set user-agent property. ** */
public final class GoogleSearch {

  private GoogleSearch() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    String target = "";
    for (String arg: args)
      target = target.concat(arg).concat(" ");
    target = target.trim();
    QueryString query = new QueryString();
    query.add("q", target);
    query.add("newwindow", "1");
    query.add("safe", "active");
    query.add("ei", "_4oFXY3oOpL39QOSvLCoBw");
    query.add("source", "hp");
    try {
      URL u = new URL("https://www.google.com/search?" + query);
      URLConnection connection = u.openConnection();
      connection.setRequestProperty(
          "User-Agent",
          "Mozilla/5.0 (Linux; Android 7.1.2;"
              + " Redmi Y1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 "
              + "Mobile Safari/537.36");
      try (InputStream in =
               new BufferedInputStream(connection.getInputStream())) {
        InputStreamReader theHTML = new InputStreamReader(in);
        int c;
        while ((c = theHTML.read()) != -1) {
          System.out.print((char)c);
        }
      }
    } catch (MalformedURLException ex) {
      System.err.println("MalformedURL : " + ex);
    } catch (IOException ex) {
      System.err.println("IOException : " + ex);
    }
  }
}
