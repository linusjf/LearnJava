package networking;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public enum AllHeaders {
  ;

  public static void main(String[] args) {
    for (String arg : args) {
      try {
        URL u = new URL(arg);
        URLConnection uc = u.openConnection();
        Map<String, List<String>> headers = uc.getHeaderFields();
        System.out.println("Headers for " + arg);
        for (Map.Entry<String, List<String>> entry : headers.entrySet())
          System.out.println("Header Key = " + entry.getKey()
                             + ", Header Values = " + entry.getValue());
      } catch (MalformedURLException ex) {
        System.err.println(arg + " is not a URL I understand.");
      } catch (IOException ex) {
        System.err.println(ex);
      }
      System.out.println();
    }
  }
}
