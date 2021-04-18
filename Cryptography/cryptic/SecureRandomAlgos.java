package cryptic;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Set;

public final class SecureRandomAlgos {

  private SecureRandomAlgos() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    final Set<String> algorithms = Security.getAlgorithms("SecureRandom");

    for (String algorithm: algorithms)
      System.out.println(algorithm);

    final String defaultAlgorithm = new SecureRandom().getAlgorithm();
    System.out.println("default: " + defaultAlgorithm);
  }
}
