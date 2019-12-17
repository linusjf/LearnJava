package stefano.lupo;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public enum TestProxy {
  ;
  private static final String FILE = "urls.txt";  // NOPMD

  private static final String PROXY_HOST = "localhost";

  @SuppressWarnings("checkstyle:magicnumber")
  private static final int PROXY_PORT = 8085;

  /**
   * Main program.
   *
   * @param args list of String arguments
   */
  public static void main(String... args) {
    String fileName = args.length > 0 ? args[0] : FILE;

    String[] urls = readURLsFromFile(fileName);
    testURLs(urls);
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private static String[] readURLsFromFile(
      String fileName) {
    try (BufferedReader reader =
             Files.newBufferedReader(Paths.get(fileName))) {
      List<String> lines = new ArrayList<>();

      String line;
      while ((line = reader.readLine()) != null)
        lines.add(line);
      return lines.toArray(new String[0]);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    return new String[0];
  }

  private static void testURLs(String... urls) {
    for (String strUrl: urls) {
      try {
        connect(strUrl);
      } catch (IOException e) {
        System.err.println(
            "Error creating HTTP(S) connection: "
            + e.getMessage());
      }
    }
  }

  private static void connect(String strUrl)
      throws IOException {
    System.out.println("Connecting to ..." + strUrl);
    if (strUrl.startsWith("http")) {
      URL url = new URL(strUrl);
      Proxy proxy = new Proxy(
          Proxy.Type.HTTP,
          new InetSocketAddress(PROXY_HOST, PROXY_PORT));
      connect(url, proxy);
    }
  }

  private static void connect(URL url, Proxy proxy)
      throws IOException {
    HttpURLConnection connection =
        (HttpURLConnection)url.openConnection(proxy);
    processConnection(connection);
  }

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  private static void processConnection(
      HttpURLConnection connection) throws IOException {
    connection.connect();
    System.out.println("Using proxy: "
                       + connection.usingProxy());
    connection.getInputStream();
    int responseCode = connection.getResponseCode();
    if (responseCode / 100 == 2)
      System.out.println("Connection successful");
    connection.disconnect();
  }
}
