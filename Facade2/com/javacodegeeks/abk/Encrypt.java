package com.javacodegeeks.abk;

import java.security.SecureRandom;

/**
 * Describe interface <code>Encrypt</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface Encrypt {
  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  String encrypt(String text);
  
  // Add salt
  /**
   * Describe <code>getSalt</code> method here.
   *
   * @return a <code>byte[]</code> value
   */
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
