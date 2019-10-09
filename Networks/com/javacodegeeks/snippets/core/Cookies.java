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

      Map<String, List<String>> headers = conn.getHeaderFields();
      Map<String, List<String>> copyHeaders = new HashMap<>();
      copyHeaders.putAll(headers);
      copyHeaders.put("NULL", copyHeaders.remove(null));
      Map<String, List<String>> headersTree = new TreeMap<>(
        String.CASE_INSENSITIVE_ORDER
      );
      headersTree.putAll(copyHeaders);

      List<String> headerFieldValue = headersTree.get("Set-Cookie");
      System.out.println(headerFieldValue);

      for (String headerValue : headerFieldValue) {
        System.out.println("Cookie Found...");
        String[] fields = headerValue.split(";\\s*");
        String cookieValue = fields[0];
        String expires = null;
        String path = null;
        String domain = null;
        boolean secure = false;

        // Parse each field
        for (int j = 1; j < fields.length; j++) {
          if ("secure".equalsIgnoreCase(fields[j])) {
            secure = true;
            continue;
          }
          if (fields[j].indexOf('=') > 0) {
            String[] f = fields[j].split("=");
            if ("expires".equalsIgnoreCase(f[0])) {
              expires = f[1];
              continue;
            }
            if ("domain".equalsIgnoreCase(f[0])) {
              domain = f[1];
              continue;
            }
            if ("path".equalsIgnoreCase(f[0])) {
              path = f[1];
              continue;
            }
          }
        }

        System.out.println("cookieValue:" + cookieValue);
        System.out.println("expires:" + expires);
        System.out.println("path:" + path);
        System.out.println("domain:" + domain);
        System.out.println("secure:" + secure);

        System.out.println("*****************************************");
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
