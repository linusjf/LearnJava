package networking;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Locale;

public class NoGovernmentCookies implements CookiePolicy {

  @Override
  public boolean shouldAccept(URI uri, HttpCookie cookie) {
    return !(uri.getAuthority().toLowerCase(Locale.getDefault()).endsWith(".gov")
        || cookie.getDomain().toLowerCase(Locale.getDefault()).endsWith(".gov"));
  }
}
