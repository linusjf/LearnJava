package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public final class Last24 {
  private static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private Last24() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    last24hoursFiles(new Date(), args);
  }

  private static void last24hoursFiles(Date today, String... args) {
    for (String arg : args) {
      try {
        System.out.println("Retrieving ...." + arg);
        URL u = new URL(arg);
        URLConnection uc = u.openConnection();
        read(uc, today);
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private static void read(URLConnection uc, Date today) throws IOException {
    System.out.println("Original if modified since: " + new Date(uc.getIfModifiedSince()));
    uc.setIfModifiedSince(new Date(today.getTime() - MILLISECONDS_PER_DAY).getTime());
    System.out.println(
        "Will retrieve file if it's modified since " + new Date(uc.getIfModifiedSince()));
    try (InputStream in = new BufferedInputStream(uc.getInputStream());
         Reader r = new InputStreamReader(in, UTF_8);) {
      int c;
      while ((c = r.read()) != -1) System.out.print((char) c);
      System.out.println();
    }
  }
}
