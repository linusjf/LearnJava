package networking;

import java.util.Date;
import java.util.Locale;

@SuppressWarnings("PMD.DataClass")
public class CacheControl {
  private Date maxAge;
  private Date sharedMaxAge;
  private boolean mustRevalidate;
  private boolean noCache;
  private boolean noStore;
  private boolean proxyRevalidate;
  private boolean publicCache;
  private boolean privateCache;

  public CacheControl(String s) {
    if (s == null || !s.contains(":")) {
      return;
      // default policy
    }
    String value = s.split(":")[1].trim();
    String[] components =
        value.toLowerCase(Locale.US).split(",");
    for (String component: components) {
      component = component.trim();
      parseValues(component);
    }
  }

  private void parseValues(String component) {
    if (component.startsWith("max-age=")) {
      int secondsInTheFuture =
          Integer.parseInt(component.substring(8));
      maxAge = new Date(System.currentTimeMillis()
                        + 1000L * secondsInTheFuture);
    } else if (component.startsWith("s-maxage=")) {
      int secondsInTheFuture =
          Integer.parseInt(component.substring(9));
      sharedMaxAge = new Date(System.currentTimeMillis()
                              + 1000L * secondsInTheFuture);
    } else if ("must-revalidate".equals(component)) {
      mustRevalidate = true;
    } else if ("proxy-revalidate".equals(component)) {
      proxyRevalidate = true;
    } else if ("no-cache".equals(component)) {
      noCache = true;
    } else if ("public".equals(component)) {
      publicCache = true;
    } else if ("private".equals(component)) {
      privateCache = true;
    }
  }

  public Date getMaxAge() {
    return new Date(maxAge.getTime());
  }

  public Date getSharedMaxAge() {
    return new Date(sharedMaxAge.getTime());
  }

  public boolean isMustRevalidate() {
    return mustRevalidate;
  }

  public boolean isProxyRevalidate() {
    return proxyRevalidate;
  }

  public boolean isNoStore() {
    return noStore;
  }

  public boolean isNoCache() {
    return noCache;
  }

  public boolean isPublicCache() {
    return publicCache;
  }

  public boolean isPrivateCache() {
    return privateCache;
  }
}
