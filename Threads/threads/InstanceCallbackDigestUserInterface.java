package threads;

import java.util.Arrays;
import javax.xml.bind.DatatypeConverter;

public class InstanceCallbackDigestUserInterface {
  private String filename;
  private byte[] digest;

  public InstanceCallbackDigestUserInterface(String filename) {
    this.filename = filename;
  }

  void receiveDigest(byte[] digest) {
    this.digest = Arrays.copyOf(digest, digest.length);
    System.out.println(this);
  }

  @Override
  public String toString() {
    String result = filename + ": ";
    if (digest != null) {
      result = result.concat(DatatypeConverter.printBase64Binary(digest));
      return result;
    }
    result = result.concat("digest not available");
    return result;
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
    for (String filename : args) {
      // Calculate the digest
      calculateDigest(filename);
    }
  }
}
