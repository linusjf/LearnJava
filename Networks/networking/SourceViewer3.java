package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class SourceViewer3 {
  private SourceViewer3() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    for (String arg : args) {
      try {
        // Open the URLConnection for reading
        URL u = new URL(arg);
        HttpURLConnection uc = (HttpURLConnection)u.openConnection();
        int code = uc.getResponseCode();
        String response = uc.getResponseMessage();
        System.out.println("HTTP/1.x " + code + " " + response);
        for (int j = 1;; j++) {
          String header = uc.getHeaderField(j);
          String key = uc.getHeaderFieldKey(j);
          if (header == null || key == null)
            break;
          System.out.println(key + ": " + header);
        }
        System.out.println();
        try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
          // chain the InputStream to a Reader
          Reader r = new InputStreamReader(in);
          int c;
          while ((c = r.read()) != -1) {
            System.out.print((char)c);
          }
        }
      } catch (MalformedURLException ex) {
        System.err.println(arg + " is not a parseable URL");
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
