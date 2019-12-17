package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class FormPoster {
  private final URL url;
  private final QueryString query = new QueryString();

  public FormPoster(URL url) {
    if (!url.getProtocol()
             .toLowerCase(Locale.getDefault())
             .startsWith("http")) {
      throw new IllegalArgumentException(
          "Posting only works for http URLs");
    }
    this.url = url;
  }

  public void add(String name, String value) {
    query.add(name, value);
  }

  public URL getURL() {
    return this.url;
  }

  public InputStream post() throws IOException {
    // open the connection and prepare it to POST
    URLConnection uc = url.openConnection();
    uc.setDoOutput(true);
    try (OutputStreamWriter out = new OutputStreamWriter(
             uc.getOutputStream(), "UTF-8")) {
      // The POST line, the Content-type header,
      // and the Content-length headers are sent by the URLConnection.
      // We just need to send the data
      out.write(query.toString());
      out.write("\r\n");
      out.flush();
    }

    // Return the response
    return uc.getInputStream();
  }

  @SuppressWarnings({"PMD.DoNotCallSystemExit",
                     "PMD.DataflowAnomalyAnalysis"})
  public static void
  main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
      System.err.println(e.getMessage());
      System.exit(1);
    });

    URL url = getDefaultURL();
    if (args.length > 0) {
      try {
        url = new URL(args[0]);
        System.out.printf("Connecting to %s%n", url);
      } catch (MalformedURLException ex) {
        System.err.println(ex.getMessage());
        System.err.printf("Connecting to %s instead%n",
                          url);
      }
    }
    FormPoster poster = new FormPoster(url);
    poster.add("name", "Elliotte Rusty Harold");
    poster.add("email", "elharo@ibiblio.org");
    try (InputStream in = poster.post()) {
      // Read the response
      Reader r = new InputStreamReader(
          in, StandardCharsets.UTF_8.name());
      int c;
      while ((c = r.read()) != -1)
        System.out.print((char)c);
      System.out.println();
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private static URL getDefaultURL() {
    try {
      return new URL(
          "http://www.cafeaulait.org/books/jnp4/postquery.phtml");
    } catch (MalformedURLException mue) {
      throw new AssertionError("This should not happen:",
                               mue);
    }
  }
}
