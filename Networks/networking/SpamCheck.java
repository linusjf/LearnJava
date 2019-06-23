package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

/**
 * This program no longer works as-is. It does not produce the desired results.
 * Using the LOOKUP url and the reversed ip lists produces different results.
 */
public final class SpamCheck {
  public static final String SPAM_LISTER = "sbl.spamhaus.org";
  public static final String POLICY_LISTER = "pbl.spamhaus.org";
  public static final String EXPLOIT_LISTER = "xbl.spamhaus.org";
  public static final String LOOKUP = "https://www.spamhaus.org/lookup/ip?";

  private SpamCheck() {
    throw new IllegalStateException("Private constructor");
  }

  private static QueryString getQueryString() {
    QueryString query = new QueryString();
    query.add("submit", "lookup");
    return query;
  }

  public static void main(String[] args) {
    for (String arg : args) {
      if (isInSpammerLists(arg)) {
        System.out.println(arg + " is a known spammer.");
      } else {
        System.out.println(arg + " appears legitimate.");
      }
    }
  }

  private static boolean isValidAddress(String reversedIp, String listAddress) {
    try {
      InetAddress.getByName(reversedIp + listAddress);
      return true;
    } catch (UnknownHostException ue) {
      System.err.println(ue.getMessage());
      return false;
    }
  }

  private static boolean isSpammer(String arg) throws UnknownHostException {
    InetAddress address = InetAddress.getByName(arg);
    byte[] quad = address.getAddress();
    String query = "";
    for (byte octet : quad) {
      int unsignedByte = octet < 0 ? octet + 256 : octet;
      query = String.valueOf(unsignedByte).concat(".").concat(query);
    }
    return isValidAddress(query, SPAM_LISTER)
        || isValidAddress(query, POLICY_LISTER)
        || isValidAddress(query, EXPLOIT_LISTER);
  }

  private static boolean isInSpammerLists(String ip) {
    QueryString query = getQueryString();
    query.add("ip", ip);

    try {
      URL u = new URL(LOOKUP + query);
      URLConnection connection = u.openConnection();
      connection.setRequestProperty(
          "User-Agent",
          "Mozilla/5.0 (Linux; Android 7.1.2;"
              + " Redmi Y1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 "
              + "Mobile Safari/537.36");
      StringBuilder sb = new StringBuilder();
      try (InputStream in =
               new BufferedInputStream(connection.getInputStream())) {
        InputStreamReader theHTML = new InputStreamReader(in);
        int c;
        while ((c = theHTML.read()) != -1) {
          sb.append((char)c);
        }
        return isIpFlagged(sb);
      }
    } catch (MalformedURLException ex) {
      System.err.println("MalformedURL : " + ex);
    } catch (IOException ex) {
      System.err.println("IOException : " + ex);
    }
    return false;
  }

  private static boolean isIpFlagged(StringBuilder sb) {
    return false;
  }
}
