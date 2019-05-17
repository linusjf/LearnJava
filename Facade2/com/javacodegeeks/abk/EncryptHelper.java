package com.javacodegeeks.abk;

import java.security.SecureRandom;

/**
 * Describe class <code>EncryptHelper</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class EncryptHelper {
  // Add salt
  /**
   * Describe <code>getSalt</code> method here.
   *
   * @return a <code>byte[]</code> value
   */
  @SuppressWarnings("Move this to Encrypt interface. Supported in Java 9.")
  public static byte[] getSalt() {
    // Always use a SecureRandom generator
    SecureRandom sr = new SecureRandom();
    // Create array for salt
    // 128 bit salt  more than enough to ensure uniqueness
    byte[] salt = new byte[16];
    // Get a random salt
    sr.nextBytes(salt);
    // return salt
    return salt;
  }
}
