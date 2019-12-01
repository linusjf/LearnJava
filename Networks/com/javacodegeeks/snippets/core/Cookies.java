package com.javacodegeeks.snippets.core;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Cookies {

  private Cookies() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      URL url = new URL("https://www.spamhaus.org");
      URLConnection conn = url.openConnection();
      printConnectionCookies(conn);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static void printConnectionCookies(URLConnection conn)
      throws IOException {

    Map<String, List<String>> headers = conn.getHeaderFields();
    Map<String, List<String>> copyHeaders = new HashMap<>();
    copyHeaders.putAll(headers);
    copyHeaders.put("NULL", copyHeaders.remove(null));
    Map<String, List<String>> headersTree =
        new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    headersTree.putAll(copyHeaders);

    List<String> headerFieldValue = headersTree.get("Set-Cookie");
    System.out.println(headerFieldValue);

    for (String headerValue: headerFieldValue) {
      printCookie(headerValue);
    }
  }

  private static void printCookie(String headerValue) {

    System.out.println("Cookie Found...");
    String[] fields = headerValue.split(";\\s*");
    Cookie cookie = new Cookie();
    cookie.cookieValue = fields[0];

    parseFieldsIntoCookie(fields, cookie);

    System.out.println("cookieValue:" + cookie.cookieValue);
    System.out.println("expires:" + cookie.expires);
    System.out.println("path:" + cookie.path);
    System.out.println("domain:" + cookie.domain);
    System.out.println("secure:" + cookie.secure);

    System.out.println("*****************************************");
  }

  private static void parseFieldsIntoCookie(String[] fields, Cookie cookie) {

    // Parse each field
    for (int j = 1; j < fields.length; j++) {
      if ("secure".equalsIgnoreCase(fields[j])) {
        cookie.secure = true;
        continue;
      }
      String[] f = splitOn(fields, j, "=");
      if ("expires".equalsIgnoreCase(f[0])) {
        cookie.expires = f[1];
        continue;
      }
      if ("domain".equalsIgnoreCase(f[0])) {
        cookie.domain = f[1];
        continue;
      }
      if ("path".equalsIgnoreCase(f[0])) {
        cookie.path = f[1];
        continue;
      }
    }
  }

  private static String[] splitOn(String[] values,
                                  int index,
                                  String separator) {
    return new String(values[index]).split(separator);
  }

  static final class Cookie {
    String cookieValue;
    String expires;
    String path;
    String domain;
    boolean secure;
  }
}
