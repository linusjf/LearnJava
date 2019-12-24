package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class HttpsClient {
  private static final int PORT = 443;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private HttpsClient() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("checkstyle:innerassignment")
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java HttpsClient host");
      return;
    }

    // default https port
    String host = args[0];
    SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    connect(host, factory);
  }

  private static int getLength(String input) {
    if (input != null) {
      try {
        return Integer.parseInt(input.trim(), 16);
      } catch (NumberFormatException ex) {
        System.err.println(
            "This server doesn't send the content-length in the first line of the response body.");
      }
    }
    return Integer.MAX_VALUE;
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private static void connect(String host, SSLSocketFactory factory) {
    try (SSLSocket socket = (SSLSocket) factory.createSocket(host, PORT)) {
      // enable all the suites
      String[] supported = socket.getSupportedCipherSuites();
      socket.setEnabledCipherSuites(supported);
      Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");

      // https requires the full URL in the GET line
      out.write("GET http://" + host + "/ HTTP/1.1\r\n");
      out.write("Host: " + host + "\r\n");
      out.write("\r\n");
      out.flush();

      // read response
      try (BufferedReader in =
          new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8))) {
        // read the header
        String s = in.readLine();
        while (!"".equals(s)) {
          System.out.println(s);
          s = in.readLine();
        }
        System.out.println();

        // read the length
        String contentLength = in.readLine();
        int length = getLength(contentLength);
        System.out.println(length);
        int c;
        int i = 0;
        while ((c = in.read()) != -1 && i++ < length) System.out.write(c);
        System.out.println();
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
