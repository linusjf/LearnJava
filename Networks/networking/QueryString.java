package networking;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SuppressWarnings("PMD.AvoidStringBufferField")
public class QueryString {
  private StringBuilder query;

  public QueryString() {
    query = new StringBuilder();
  }

  public void add(String name, String value) {
    synchronized (query) {
      query.append('&');
      encode(name, value);
    }
  }

  private void encode(String name, String value) {
    try {
      query.append(URLEncoder.encode(name, "UTF-8"));
      query.append('=');
      query.append(URLEncoder.encode(value, "UTF-8"));
    } catch (UnsupportedEncodingException ex) {
      throw new EncodingException("Broken VM does not support UTF-8", ex);
    }
  }

  public String getQuery() {
    synchronized (query) {
      return query.toString();
    }
  }

  @Override
  public String toString() {
    return getQuery();
  }

  public static void main(String[] args) {
    QueryString qs = new QueryString();
    qs.add("hl", "en");
    qs.add("as_q", "Java");
    qs.add("as_epq", "I/O");
    String url = "http://www.google.com/search?" + qs;
    System.out.println(url);
  }
}
