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
    super();
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
}
