package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class SourceViewer3 {

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private SourceViewer3() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    for (String arg: args) {
      try {
        // Open the URLConnection for reading
        URL u = new URL(arg);
        HttpURLConnection uc = (HttpURLConnection)u.openConnection();
        printSource(uc);
      } catch (MalformedURLException ex) {
        System.err.println(arg + " is not a parseable URL");
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private static void printSource(HttpURLConnection uc) throws IOException {
    int code = uc.getResponseCode();
    String response = uc.getResponseMessage();
    System.out.println("HTTP/1.x " + code + " " + response);
    int j = 1;
    String header;
    String key;
    while ((header = uc.getHeaderField(j)) != null
           && (key = uc.getHeaderFieldKey(j)) != null) {
      System.out.println(key + ": " + header);
      j++;
    }
    System.out.println();
    try (InputStream in = new BufferedInputStream(uc.getInputStream());
         Reader r = new InputStreamReader(in, UTF_8);) {
      int c;
      while ((c = r.read()) != -1)
        System.out.print((char)c);
    }
  }
}
