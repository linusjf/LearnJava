package networking;

import java.util.Date;
import java.util.Locale;

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
      return;  // default policy
    }
    String value = s.split(":")[1].trim();
    String[] components = value.toLowerCase(Locale.US).split(",");
    for (String component : components) {
      component = component.trim();
      parseValues(component);
    }
  }

  private void parseValues(String component) {
    Date now = new Date();
    if (component.startsWith("max-age=")) {
      int secondsInTheFuture = Integer.parseInt(component.substring(8));
      maxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
    } else if (component.startsWith("s-maxage=")) {
      int secondsInTheFuture = Integer.parseInt(component.substring(9));
      sharedMaxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
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
    return maxAge;
  }

  public Date getSharedMaxAge() {
    return sharedMaxAge;
  }

  public boolean mustRevalidate() {
    return mustRevalidate;
  }

  public boolean proxyRevalidate() {
    return proxyRevalidate;
  }

  public boolean noStore() {
    return noStore;
  }

  public boolean noCache() {
    return noCache;
  }

  public boolean publicCache() {
    return publicCache;
  }

  public boolean privateCache() {
    return privateCache;
  }
}
