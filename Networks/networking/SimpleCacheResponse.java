package networking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SimpleCacheResponse extends CacheResponse {
  private final Map<String, List<String>> headers;
  private final SimpleCacheRequest request;
  private final Date expires;
  private final CacheControl control;

  public SimpleCacheResponse(SimpleCacheRequest request, URLConnection uc, CacheControl control)
      throws IOException {
    super();
    this.request = request;
    this.control = control;
    this.expires = new Date(uc.getExpiration());
    this.headers = Collections.unmodifiableMap(uc.getHeaderFields());
  }

  @Override
  public InputStream getBody() {
    return new ByteArrayInputStream(request.getData());
  }

  @Override
  public Map<String, List<String>> getHeaders() throws IOException {
    return headers;
  }

  public CacheControl getControl() {
    return control;
  }

  public boolean isExpired() {
    Date now = new Date();
    if (control.getMaxAge().before(now)) return true;
    if (expires != null) return expires.before(now);
    return false;
  }
}
