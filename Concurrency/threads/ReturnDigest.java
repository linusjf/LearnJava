package threads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ReturnDigest extends Thread {
  private final String filename;
  private byte[] digest = new byte[0];

  public ReturnDigest(String filename) {
    this.filename = filename;
  }

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
      setDigest(sha.digest());
    } catch (IOException | NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }

  private void setDigest(byte[] digest) {
    this.digest = Arrays.copyOf(digest, digest.length);
  }

  public byte[] getDigest() {
    return Arrays.copyOf(digest, digest.length);
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof ReturnDigest))
      return false;
    ReturnDigest other = (ReturnDigest)o;
    if (!other.canEqual((Object)this))
      return false;
    if (!super.equals(o))
      return false;
    Object this$filename = this.filename;
    Object other$filename = other.filename;
    if (this$filename == null ? other$filename != null
                              : !this$filename.equals(other$filename))
      return false;
    if (!java.util.Arrays.equals(this.getDigest(), other.getDigest()))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof ReturnDigest;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    Object $filename = this.filename;
    result = result * PRIME + ($filename == null ? 43 : $filename.hashCode());
    result = result * PRIME + java.util.Arrays.hashCode(this.getDigest());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "ReturnDigest(filename=" + this.filename
        + ", digest=" + java.util.Arrays.toString(this.getDigest()) + ")";
  }
}
