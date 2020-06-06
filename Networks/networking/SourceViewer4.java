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

public final class SourceViewer4 {

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private SourceViewer4() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      URL u = new URL(args[0]);
      HttpURLConnection uc = (HttpURLConnection)u.openConnection();
      printFromConnection(uc);
    } catch (MalformedURLException ex) {
      System.err.println(args[0] + " is not a parseable URL");
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private static void printFromConnection(HttpURLConnection uc)
      throws IOException {
    try (InputStream raw = uc.getInputStream()) {
      printFromStream(raw);
    } catch (IOException ex) {
      System.err.println(uc.getURL());
      printFromStream(uc.getErrorStream());
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private static void printFromStream(InputStream raw) throws IOException {
    try (InputStream buffer = new BufferedInputStream(raw);
         Reader reader = new InputStreamReader(buffer, UTF_8);) {
      int c;
      while ((c = reader.read()) != -1)
        System.out.print((char)c);
    }
  }
}
