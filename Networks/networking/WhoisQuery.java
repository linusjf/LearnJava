package networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WhoisQuery {

  public static void main(String[] args) throws Exception {
    String domainNameToCheck = "abcnews.com";
    performWhoisQuery("whois.enom.com", 43, domainNameToCheck);
    performWhoisQuery("whois.internic.net", 43, domainNameToCheck);
  }

  public static void performWhoisQuery(String host, int port, String query)
      throws Exception {
    System.out.println("**** Performing whois query for '" + query + "' at "
                       + host + ":" + port);

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
