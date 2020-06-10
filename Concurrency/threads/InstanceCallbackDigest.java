package threads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable {
  private final String filename;
  private final Receiver callback;

  public InstanceCallbackDigest(String filename, Receiver callback) {
    this.filename = filename;
    this.callback = callback;
  }

  @Override
  @SuppressWarnings({"PMD.EmptyWhileStmt", "PMD.LawOfDemeter"})
  public void run() {
    try {
      InputStream in = Files.newInputStream(Paths.get(filename));
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);
      while (din.read() != -1) ;
      din.close();
      byte[] digest = sha.digest();
      callback.receiveDigest(digest);
    } catch (IOException | NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof InstanceCallbackDigest)) return false;
    InstanceCallbackDigest other = (InstanceCallbackDigest) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$filename = this.filename;
    Object other$filename = other.filename;
    if (this$filename == null ? other$filename != null : !this$filename.equals(other$filename)) return false;
    Object this$callback = this.callback;
    Object other$callback = other.callback;
    if (this$callback == null ? other$callback != null : !this$callback.equals(other$callback)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof InstanceCallbackDigest;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $filename = this.filename;
    result = result * PRIME + ($filename == null ? 43 : $filename.hashCode());
    Object $callback = this.callback;
    result = result * PRIME + ($callback == null ? 43 : $callback.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "InstanceCallbackDigest(filename=" + this.filename + ", callback=" + this.callback + ")";
  }
}
