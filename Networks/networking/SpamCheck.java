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
 * non-browser queries? Upon investigating further,the cookies are from
 * Cloudflare and its DDOS challenge question.cf_clearance expires every hour.
 * It has to be renewed via the browser. There is a Python module on Github that
 * bypasses Cloudflare but I'm not going to port that just to get this working.
 * I have simply tried to get the original SpamCheck program from the Java
 * Networking book to work as expected. I have looked at other spam listers but
 * none of them list the two spammer ips Ive used as input as spammers. Spamhaus
 * appears to be the most authoriative list.
 */
public final class SpamCheck {
  private static final MessageFormat SPAM_LISTER =
      new MessageFormat("{0} is listed in the SBL");
  private static final MessageFormat POLICY_LISTER =
      new MessageFormat("{0} is listed in the PBL");
  private static final MessageFormat EXPLOIT_LISTER =
      new MessageFormat("{0} is listed in the XBL");
  private static final String LOOKUP = "https://www.spamhaus.org/lookup/ip/?";
  private static String cookies;

  static {
    constructCookieString();
  }

  @SuppressWarnings("PMD.ConsecutiveLiteralAppends")
  private static void constructCookieString() {
    StringBuilder sb = new StringBuilder(200);
    sb.append("__cfduid=d1c401e353768541acd788ffac40686911560481116; ")
        .append("_ga=GA1.2.258026625.1560481121; ")
        .append("_gid=GA1.2.1507765202.1561264155; ")
        .append(
            "cf_clearance=5bf5acbbc9de97ae5421ff665219fe913ecd7640-1561308998-28800-150");
    cookies = sb.toString();
  }

  private SpamCheck() {
    throw new IllegalStateException("Private constructor");
  }

  private static QueryString getQueryString() {
    return new QueryString();
  }

  private static String getCookies() {
    return cookies;
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
    connection.setRequestProperty("Cookie", getCookies());
    StringBuilder sb = new StringBuilder();
    InputStream in = new BufferedInputStream(connection.getInputStream());
    InputStreamReader theHTML = new InputStreamReader(in);
    int c;
    while ((c = theHTML.read()) != -1) {
      sb.append((char)c);
    }
    return isIpFlagged(sb.toString(), ip);
  }

  @SuppressWarnings("PMD.OneDeclarationPerLine")
  private static boolean isIpFlagged(String content, String ip) {
    String[] params = new String[] {ip};
    String sblString, xblString, pblString;
    synchronized (params) {
      sblString = SPAM_LISTER.format(params);
      xblString = EXPLOIT_LISTER.format(params);
      pblString = POLICY_LISTER.format(params);
    }
    return content.contains(sblString) || content.contains(xblString)
        || content.contains(pblString);
  }
}
