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
  private String filename;
  private byte[] digest;

  public ReturnDigest(String filename) {
    this.filename = filename;
  }

  @Override
  @SuppressWarnings("PMD.EmptyWhileStmt")
  public void run() {
    try {
      InputStream in = Files.newInputStream(Paths.get(filename));
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);
      while (din.read() != -1)
        ;  // read entire file
      din.close();
      digest = sha.digest();
    } catch (IOException | NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }

  public byte[] getDigest() {
    return Arrays.copyOf(digest, digest.length);
  }
}
