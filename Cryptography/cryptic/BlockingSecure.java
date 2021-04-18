package cryptic;

import java.security.SecureRandom;

public final class BlockingSecure {

  private BlockingSecure() {
    throw new IllegalStateException("Private Constructor");
  }

  @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  public static void main(String[] args) {
    int out = 0;
    for (int i = 0; i < 1 << 20; i++) 
      out ^= new SecureRandom().nextInt();
    System.out.println(out);
  }
}
