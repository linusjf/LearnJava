package com.javacodegeeks.abk;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Describe class <code>SHA224</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SHA224 implements Encrypt {
  private static final Base64.Encoder ENCODER = Base64.getEncoder();

  private static final Charset UTF_8 = StandardCharsets.UTF_8;

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
      MessageDigest digest = MessageDigest.getInstance("SHA-224");
      final byte[] textBytes = digest.digest(text.getBytes(UTF_8));
      return ENCODER.encodeToString(textBytes);
    } catch (NoSuchAlgorithmException e) {
      throw new AssertionError("Algorithm not found : ", e);
    }
  }
}
