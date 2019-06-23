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
 * Using the LOOKUP url returns a HTTP 503 error. Is this SpamHaus throttling
 * non-browser queries? Spamhaus API available?
 */
public final class SpamCheck {
  public static final MessageFormat SPAM_LISTER =
      new MessageFormat("{0} is listed in the SBL");
  public static final MessageFormat POLICY_LISTER =
      new MessageFormat("{0} is listed in the PBL");
  public static final MessageFormat EXPLOIT_LISTER =
      new MessageFormat("{0} is listed in the XBL");
  public static final String LOOKUP = "https://www.spamhaus.org/lookup/ip/?";

  private SpamCheck() {
    throw new IllegalStateException("Private constructor");
  }

  private static QueryString getQueryString() {
    QueryString query = new QueryString();
    return query;
  }

  public static void main(String[] args) {
    for (String arg : args) {
      try {
        if (isInSpammerLists(arg)) {
          System.out.println(arg + " is a known spammer.");
        } else {
          System.out.println(arg + " appears legitimate.");
        }
      } catch (MalformedURLException ex) {
        System.err.println("MalformedURL : " + ex);
      } catch (IOException ex) {
        System.err.println("IOException : " + ex);
      }
    }
  }

  private static boolean isInSpammerLists(String ip)
      throws IOException, MalformedURLException {
    QueryString query = getQueryString();
    query.add("ip", ip);

    System.out.println(LOOKUP + query);
    URL u = new URL(LOOKUP + query);
    URLConnection connection = u.openConnection();
    connection.setRequestProperty(
        "User-Agent",
        "Mozilla/5.0 (Linux; Android 7.1.2;"
            + " Redmi Y1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 "
            + "Mobile Safari/537.36");
    connection.setRequestProperty(
        "Cookie",
        "__cfduid=d1c401e353768541acd788ffac40686911560481116;"
            + " _ga=GA1.2.258026625.1560481121;"
            + " _gid=GA1.2.1507765202.1561264155;"
            + " cf_clearance=b9c0ecb320d62c27fbd34500c22377ec054c3760-1561272641-28800-150");
    StringBuilder sb = new StringBuilder();
    InputStream in = new BufferedInputStream(connection.getInputStream());
    InputStreamReader theHTML = new InputStreamReader(in);
    int c;
    while ((c = theHTML.read()) != -1) {
      sb.append((char)c);
    }
    return isIpFlagged(sb.toString(), ip);
  }

  private static boolean isIpFlagged(String content, String ip) {
    String[] params = new String[] {ip};
    String sblString = SPAM_LISTER.format(params);
    String xblString = EXPLOIT_LISTER.format(params);
    String pblString = POLICY_LISTER.format(params);
    return content.contains(sblString) || content.contains(xblString)
        || content.contains(pblString);
  }
}
