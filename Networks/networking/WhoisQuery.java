package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public final class WhoisQuery {

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private WhoisQuery() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    try {
      String domainNameToCheck = "abcnews.com";
      performWhoisQuery("whois.enom.com", 43, domainNameToCheck);
      performWhoisQuery("whois.internic.net", 43, domainNameToCheck);
    } catch (IOException exc) {
      System.err.println(exc.getMessage());
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void performWhoisQuery(String host, int port, String query)
      throws UnknownHostException, IOException {
    System.out.println("**** Performing whois query for '" + query + "' at "
                       + host + ":" + port);
    try (Socket socket = new Socket(host, port);
         InputStreamReader isr =
             new InputStreamReader(socket.getInputStream(), UTF_8);
         BufferedReader in = new BufferedReader(isr);
         PrintWriter out = new PrintWriter(
             new OutputStreamWriter(socket.getOutputStream(), UTF_8), true);) {
      out.println(query);

      String line = "";
      while ((line = in.readLine()) != null)
        System.out.println(line);
    }
  }
}
