package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This program no longer works as-is. It does not produce the desired results.
 * Using the LOOKUP url returns a HTTP 503 error. Is this SpamHaus throttling
 * non-browser queries? Spamhaus API available?
 */
public final class SpamCheck {
  private static final MessageFormat SPAM_LISTER =
      new MessageFormat("{0} is listed in the SBL");
  private static final MessageFormat POLICY_LISTER =
      new MessageFormat("{0} is listed in the PBL");
  private static final MessageFormat EXPLOIT_LISTER =
      new MessageFormat("{0} is listed in the XBL");
  private static final String LOOKUP = "https://www.spamhaus.org/lookup/ip/?";
  private static final String SPAMHAUS = "https://www.spamhaus.org/lookup";
  private static String cookies;

  private SpamCheck() {
    throw new IllegalStateException("Private constructor");
  }

  private static QueryString getQueryString() {
    QueryString query = new QueryString();
    return query;
  }

  private static String getCookies() throws MalformedURLException, IOException {
    if (cookies == null) {
      /**URL url = new URL(SPAMHAUS);
      URLConnection conn = url.openConnection();

      Map<String, List<String>> headers = conn.getHeaderFields();
      Map<String, List<String>> copyHeaders = new HashMap<>();
      copyHeaders.putAll(headers);
      copyHeaders.put("NULL", copyHeaders.remove(null));
      Map<String, List<String>> headersTree =
          new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
      headersTree.putAll(copyHeaders);

      List<String> headerFieldValue = 
        headersTree.get("Set-Cookie");

      StringBuilder sb = new StringBuilder();
    for (String headerValue : headerFieldValue) {
      String[] fields = headerValue.split(";\\s*");
      //sb.append(fields[0]).append("; ");
    }*/
    StringBuilder sb = new StringBuilder();
    sb.append("__cfduid=d1c401e353768541acd788ffac40686911560481116; _ga=GA1.2.258026625.1560481121; _gid=GA1.2.1507765202.1561264155; cf_clearance=5bf5acbbc9de97ae5421ff665219fe913ecd7640-1561308998-28800-150");
    cookies = sb.toString();
    }
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
    /**connection.setRequestProperty(
        "Cookie",
        "__cfduid=d1c401e353768541acd788ffac40686911560481116;"
            + " _ga=GA1.2.258026625.1560481121;"
            + " _gid=GA1.2.1507765202.1561264155;"
            + " cf_clearance=b9c0ecb320d62c27fbd34500c22377ec054c3760-1561272641-28800-150");*/
      connection.setRequestProperty(
        "Cookie",getCookies());
      System.out.println(getCookies());
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
