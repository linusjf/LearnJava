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
    for (String filename : args) spinOffCallback(filename);
  }

  private static void spinOffCallback(String filename) {
    // Calculate the digest
    Runnable cb = new CallbackDigest(filename, new CallbackDigestUserInterface(filename));
    Thread t = new Thread(cb);
    t.start();
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "CallbackDigestUserInterface(filename=" + this.filename + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof CallbackDigestUserInterface)) return false;
    CallbackDigestUserInterface other = (CallbackDigestUserInterface) o;
    Object this$filename = this.filename;
    Object other$filename = other.filename;
    if (this$filename == null ? other$filename != null : !this$filename.equals(other$filename)) return false;
    return true;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $filename = this.filename;
    result = result * PRIME + ($filename == null ? 43 : $filename.hashCode());
    return result;
  }
}
