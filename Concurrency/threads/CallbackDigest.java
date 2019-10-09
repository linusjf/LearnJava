package threads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallbackDigest implements Runnable {
  private String filename;

  public CallbackDigest(String filename) {
    this.filename = filename;
  }

  @Override
  @SuppressWarnings("PMD.EmptyWhileStmt")
  public void run() {
    try {
      InputStream in = Files.newInputStream(Paths.get(filename));
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);
      while (din.read() != -1); // read entire file
      din.close();
      byte[] digest = sha.digest();
      CallbackDigestUserInterface.receiveDigest(digest, filename);
    } catch (IOException | NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }
}
