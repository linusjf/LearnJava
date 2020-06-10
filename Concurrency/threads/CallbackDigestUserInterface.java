package threads;

import java.util.Base64;

// for DatatypeConverter; requires Java 6 or JAXB 1.0
public final class CallbackDigestUserInterface implements Receiver {

  private final String filename;

  public CallbackDigestUserInterface(String filename) {
    this.filename = filename;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void receiveDigest(byte[] digest) {
    StringBuilder result = new StringBuilder(filename);
    result.append(": ").append(Base64.getEncoder().encodeToString(digest));
    System.out.println(result);
  }

  public static void main(String[] args) {
    for (String filename: args)
      spinOffCallback(filename);
  }

  private static void spinOffCallback(String filename) {
    // Calculate the digest
    Runnable cb =
        new CallbackDigest(filename, new CallbackDigestUserInterface(filename));
    Thread t = new Thread(cb);
    t.start();
  }
}
