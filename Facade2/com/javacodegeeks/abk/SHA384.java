package com.javacodegeeks.abk;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Describe class <code>SHA384</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SHA384 implements Encrypt {
  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public String encrypt(String text) {
    try {
      final MessageDigest digest = MessageDigest.getInstance("SHA-384");
      final byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(textBytes);
    } catch (NoSuchAlgorithmException e) {
      throw new AssertionError("Algorithm not found : ", e);
    }
  }
}
