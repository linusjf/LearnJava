package stefano.lupo;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public enum TestProxy {
  ;

  private static final String FILE = "urls.txt"; // NOPMD

  private static final String PROXY_HOST = "localhost";

  @SuppressWarnings("checkstyle:magicnumber") private static final int PROXY_PORT = 8085;

  /**
   * Main program.
   *
   * @param args list of String arguments
   */
  public static void main(String... args) {
    String fileName = FILE;
    if (args.length > 0)
      fileName = args[0];

    String[] urls = readURLsFromFile(fileName);
    testURLs(urls);
  }

  private static String[] readURLsFromFile(String fileName) {
    String[] data = new String[] {};
    try {
      BufferedReader reader = Files.newBufferedReader(Paths.get(fileName));
      List<String> lines = new ArrayList<>();

      String line = reader.readLine();
      while (line != null) {
        lines.add(line);
        line = reader.readLine();
      }
      reader.close();
      data = lines.toArray(new String[] {});
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    return data;
  }

  private static void testURLs(String... urls) {
    for (String strUrl : urls) {
      try {
        connect(strUrl);
      } catch (IOException e) {
        System.err.println("Error creating HTTP(S) connection: " + e.getMessage());
      }
    }
  }

  private static void connect(String strUrl) throws IOException {
    URL url = new URL(strUrl);
    System.out.println("Connecting to ..." + strUrl);
    if (strUrl.startsWith("http")) {
      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
      URLConnection connection = url.openConnection(proxy);
      connection.getInputStream();
    }
  }
}
