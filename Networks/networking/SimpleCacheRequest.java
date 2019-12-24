package networking;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

public class SimpleCacheRequest extends CacheRequest {
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();

  @Override
  public OutputStream getBody() throws IOException {
    return out;
  }

  @Override
  public void abort() {
    out.reset();
  }

  public byte[] getData() {
    if (out.size() == 0) return new byte[0];
    else return out.toByteArray();
  }
}
