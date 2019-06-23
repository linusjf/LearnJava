package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 * This program no longer works as-is. It does not produce the desired results.
 * Using the LOOKUP url returns a HTTP 503 error.
 * Is this SpamHaus throttling non-browser queries?
 * Spamhaus API available?
 */
public final class SpamCheck {
  public static final MessageFormat SPAM_LISTER =
      new MessageFormat("{0} is listed in the SBL");
  public static final MessageFormat POLICY_LISTER =
      new MessageFormat("{0} is listed in the PBL");
  public static final MessageFormat EXPLOIT_LISTER =
      new MessageFormat("{0} is listed in the XBL");
  public static final String LOOKUP = "https://www.spamhaus.org/lookup/ip?";

  private SpamCheck() {
    throw new IllegalStateException("Private constructor");
  }

  private static QueryString getQueryString() {
    QueryString query = new QueryString();
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

  private static boolean isInSpammerLists(String ip) {
    QueryString query = getQueryString();
    query.add("ip", ip);
    try {
      System.out.println(LOOKUP + query);
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
        return isIpFlagged(sb.toString(), ip);
      }
    } catch (MalformedURLException ex) {
      System.err.println("MalformedURL : " + ex);
    } catch (IOException ex) {
      System.err.println("IOException : " + ex);
    }
    return false;
  }

  private static boolean isIpFlagged(String content, String ip) {
    System.out.println(content);
    String[] params = new String[] {ip};
    String sblString = SPAM_LISTER.format(params);
    System.out.println(sblString);
    String xblString = EXPLOIT_LISTER.format(params);
    System.out.println(xblString);
    String pblString = POLICY_LISTER.format(params);
    System.out.println(pblString);
    return content.contains(sblString) || content.contains(xblString)
        || content.contains(pblString);
  }
}
