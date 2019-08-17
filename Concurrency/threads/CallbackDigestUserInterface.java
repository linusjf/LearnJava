package threads;

import javax.xml.bind.DatatypeConverter;

// for DatatypeConverter; requires Java 6 or JAXB 1.0

public final class CallbackDigestUserInterface {
  private CallbackDigestUserInterface() {
    throw new IllegalStateException("Private constructor");
  }

  public static void receiveDigest(byte[] digest, String name) {
    StringBuilder result = new StringBuilder(name);
    result.append(": ").append(DatatypeConverter.printBase64Binary(digest));
    System.out.println(result);
  }

  public static void main(String[] args) {
    for (String filename : args) {
      spinOffCallback(filename);
    }
  }

  private static void spinOffCallback(String filename) {
    // Calculate the digest
    CallbackDigest cb = new CallbackDigest(filename);
    Thread t = new Thread(cb);
    t.start();
  }
}
