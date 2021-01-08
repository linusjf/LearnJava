package threads;

import java.util.Arrays;
import java.util.Base64;

@SuppressWarnings("PMD.SystemPrintln")
public class InstanceCallbackDigestUserInterface implements Receiver {
  private final String filename;
  private final Base64.Encoder encoder = Base64.getEncoder();
  private byte[] digest;

  public InstanceCallbackDigestUserInterface(String filename) {
    this.filename = filename;
  }

  @Override
  @SuppressWarnings("checkstyle:hiddenfield")
  public void receiveDigest(byte[] digest) {
    this.digest = Arrays.copyOf(digest, digest.length);
    System.out.println(this);
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public String toString() {
    String result = filename + ": ";
    return digest == null ? result.concat("digest not available")
                          : result.concat(encoder.encodeToString(digest));
  }

  public void calculateDigest() {
    Runnable cb = new InstanceCallbackDigest(filename, this);
    Thread t = new Thread(cb);
    t.start();
  }

  private static void calculateDigestForFile(String filename) {
    InstanceCallbackDigestUserInterface d =
        new InstanceCallbackDigestUserInterface(filename);
    d.calculateDigest();
  }

  public static void main(String[] args) {
    for (String filename: args)
      calculateDigestForFile(filename);
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof InstanceCallbackDigestUserInterface))
      return false;
    InstanceCallbackDigestUserInterface other =
        (InstanceCallbackDigestUserInterface)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$filename = this.filename;
    Object other$filename = other.filename;
    if (this$filename == null ? other$filename != null
                              : !this$filename.equals(other$filename))
      return false;
    Object this$encoder = this.encoder;
    Object other$encoder = other.encoder;
    if (this$encoder == null ? other$encoder != null
                             : !this$encoder.equals(other$encoder))
      return false;
    if (!java.util.Arrays.equals(this.digest, other.digest))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof InstanceCallbackDigestUserInterface;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $filename = this.filename;
    result = result * PRIME + ($filename == null ? 43 : $filename.hashCode());
    Object $encoder = this.encoder;
    result = result * PRIME + ($encoder == null ? 43 : $encoder.hashCode());
    result = result * PRIME + java.util.Arrays.hashCode(this.digest);
    return result;
  }
}
