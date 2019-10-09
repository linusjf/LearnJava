package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public final class WhoisQuery {

  private WhoisQuery() {
    throw new IllegalStateException("Private constructor");
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

  public static void performWhoisQuery(String host, int port, String query)
    throws UnknownHostException, IOException {
    System.out.println(
      "**** Performing whois query for '" + query + "' at " + host + ":" + port
    );

    Socket socket = new Socket(host, port);

    InputStreamReader isr = new InputStreamReader(socket.getInputStream());
    BufferedReader in = new BufferedReader(isr);

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    out.println(query);

    String line = "";
    while ((line = in.readLine()) != null) {
      System.out.println(line);
    }
  }
}
