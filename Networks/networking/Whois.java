package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EnumSet;
import java.util.regex.Pattern;

@SuppressWarnings("PMD.DataClass")
public class Whois {
  public static final int DEFAULT_PORT = 43;
  public static final String DEFAULT_HOST =
      "whois.internic.net";
  private int port = DEFAULT_PORT;
  private InetAddress host;

  public Whois(InetAddress host, int port) {
    this.host = host;
    this.port = port;
  }

  public Whois(InetAddress host) {
    this(host, DEFAULT_PORT);
  }

  public Whois(String hostname, int port)
      throws UnknownHostException {
    this(InetAddress.getByName(hostname), port);
  }

  public Whois(String hostname)
      throws UnknownHostException {
    this(InetAddress.getByName(hostname), DEFAULT_PORT);
  }

  public Whois() throws UnknownHostException {
    this(DEFAULT_HOST, DEFAULT_PORT);
  }

  // Items to search for
  public enum SearchFor {
    ANY("Any"),
    NETWORK("Network"),
    PERSON("Person"),
    HOST("Host"),
    DOMAIN("Domain"),
    ORGANIZATION("Organization"),
    GROUP("Group"),
    GATEWAY("Gateway"),
    ASN("ASN");
    private String label;

    SearchFor(String label) {
      this.label = label;
    }

    public static Pattern getRegexPattern() {
      StringBuilder pattern = new StringBuilder();
      pattern.append("(?i)^((");
      EnumSet.allOf(SearchFor.class)
          .forEach(searchfor
                   -> pattern.append('(')
                          .append(searchfor)
                          .append(")|"));
      pattern.deleteCharAt(pattern.length() - 1);
      pattern.append("){1})$");
      return Pattern.compile(pattern.toString());
    }
  }

  // Categories to search in
  public enum SearchIn {
    ALL(""),
    NAME("Name"),
    MAILBOX("Mailbox"),
    HANDLE("!");
    private String label;

    SearchIn(String label) {
      this.label = label;
    }

    public static Pattern getRegexPattern() {
      StringBuilder pattern = new StringBuilder();
      pattern.append("(?i)^((");
      EnumSet.allOf(SearchIn.class)
          .forEach(searchin
                   -> pattern.append('(')
                          .append(searchin)
                          .append(")|"));
      pattern.deleteCharAt(pattern.length() - 1);
      pattern.append("){1})$");
      return Pattern.compile(pattern.toString());
    }
  }

  public String lookUpNamesExactMatch(String target,
                                      SearchFor category,
                                      SearchIn group)
      throws IOException {
    return lookUpNames(target, category, group, "");
  }

  public String lookUpNames(String target,
                            SearchFor category,
                            SearchIn group)
      throws IOException {
    return lookUpNames(target, category, group, ".");
  }

  public String lookUpNames(String target,
                            SearchFor category,
                            SearchIn group,
                            String suffix)
      throws IOException {
    String prefix = category.label + " " + group.label;
    String query = prefix + target + suffix;
    try (Socket socket = new Socket(host, port);
         Writer out = new OutputStreamWriter(
             socket.getOutputStream(), "ASCII");
         BufferedReader in =
             new BufferedReader(new InputStreamReader(
                 socket.getInputStream(), "ASCII"));) {
      out.write(query + "\r\n");
      out.flush();
      StringBuilder response = new StringBuilder();
      String theLine = in.readLine();
      while (theLine != null) {
        response.append(theLine);
        response.append("\r\n");
        theLine = in.readLine();
      }
      return response.toString();
    }
  }

  public InetAddress getHost() {
    return this.host;
  }

  public void setHost(String host)
      throws UnknownHostException {
    this.host = InetAddress.getByName(host);
  }
}
