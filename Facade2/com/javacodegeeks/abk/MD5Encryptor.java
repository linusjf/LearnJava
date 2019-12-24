package com.javacodegeeks.abk;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Describe class <code>MD5Encryptor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class MD5Encryptor implements Encrypt {
  private static final Base64.Encoder ENCODER = Base64.getEncoder();

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
      final MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      msgDigest.update(text.getBytes(StandardCharsets.UTF_8));
      byte[] textBytes = msgDigest.digest();
      return ENCODER.encodeToString(textBytes);
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Algorithm not found : " + e.getMessage());
      throw new AssertionError("Algorithm not found : ", e);
    }
  }
}
