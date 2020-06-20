package threads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
// for DatatypeConverter; requires Java 6 or JAXB 1.0

public class DigestThread extends Thread {
  private static final Base64.Encoder ENCODER = Base64.getEncoder();
  private final String filename;

  public DigestThread(String filename) {
    this.filename = filename;
  }

  // CPD-OFF
  @Override
  @SuppressWarnings({"PMD.EmptyWhileStmt", "PMD.LawOfDemeter"})
  public void run() {
    try {
      InputStream in = Files.newInputStream(Paths.get(filename));
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);
      while (din.read() != -1)
        ;
      din.close();
      byte[] digest = sha.digest();
      StringBuilder result = new StringBuilder(filename);
      result.append(": ").append(ENCODER.encodeToString(digest));
      System.out.println(result);
    } catch (IOException | NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }

  // CPD-ON
  public static void main(String[] args) {
    System.out.println("Into DigestThread...");
    for (String filename : args) runDigestThread(filename);
  }

  private static void runDigestThread(String filename) {
    Thread t = new DigestThread(filename);
    t.start();
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof DigestThread)) return false;
    DigestThread other = (DigestThread) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    Object this$filename = this.filename;
    Object other$filename = other.filename;
    if (this$filename == null ? other$filename != null : !this$filename.equals(other$filename))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof DigestThread;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    Object $filename = this.filename;
    result = result * PRIME + ($filename == null ? 43 : $filename.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "DigestThread(filename=" + this.filename + ")";
  }
}
