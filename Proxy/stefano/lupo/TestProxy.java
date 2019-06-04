package stefano.lupo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public enum TestProxy {
  ;

  final private static String FILE = "urls.txt";// NOPMD

  final private static String PROXY_HOST = "localhost";

  @SuppressWarnings("checkstyle:magicnumber")
  final private static int PROXY_PORT = 8085;

  /**
   * Main program.
   *
   * @param args list of String arguments
   */
  public static void main(String[] args) {
    String fileName = FILE;
    if (args.length > 0) fileName = args[0];

    String[] urls = readURLsFromFile(fileName);
    testURLs(urls);
  }

  private static String[] readURLsFromFile(String fileName) {
    String[] data = new String[] {};
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      List<String> lines = new ArrayList<String>();

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

  private static void testURLs(String[] urls) {
    for (String strUrl : urls) {
      try {
        Proxy proxy = null;
        URL url = new URL(strUrl);
        System.out.println("Connecting to ..." + strUrl);
        if (strUrl.startsWith("http")) {
          proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
        URLConnection connection = url.openConnection(proxy);
        connection.getInputStream();
        }
      } catch (IOException e) {
        System.err.println("Error creating HTTP(S) connection: " + e.getMessage());
      }
    }
  }
}
