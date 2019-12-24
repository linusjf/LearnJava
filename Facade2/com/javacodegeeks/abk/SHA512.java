package com.javacodegeeks.abk;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Describe class <code>SHA512</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SHA512 implements Encrypt {
  private final Base64.Encoder encoder = Base64.getEncoder();

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
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
      return encoder.encodeToString(textBytes);
    } catch (NoSuchAlgorithmException e) {
      throw new AssertionError("Algorithm not found : ", e);
    }
  }
}
