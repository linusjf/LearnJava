package threads;

import java.util.Arrays;
import java.util.Base64;

public class InstanceCallbackDigestUserInterface {
  private final String filename;
  private final Base64.Encoder encoder =
    Base64.getEncoder();
  private byte[] digest;

  public InstanceCallbackDigestUserInterface(String filename) {
    this.filename = filename;
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  void receiveDigest(byte[] digest) {
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
    InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);
    Thread t = new Thread(cb);
    t.start();
  }

  private static void calculateDigest(String filename) {
    InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface(filename);
    d.calculateDigest();
  }

  public static void main(String[] args) {
    for (String filename : args) 
      calculateDigest(filename);
  }
}
