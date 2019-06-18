package com.javacodegeeks.abk;

import static com.lambdaworks.codec.Base64.encode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Describe class <code>SHA224</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SHA224 implements Encrypt {
  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  @Override
  public String encrypt(String text) {
    String hash = "";
    try {
      final MessageDigest digest = MessageDigest.getInstance("SHA-224");
      final byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
      hash = String.valueOf(encode(textBytes));
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Algorithm not found : " + e.getMessage());
    }
    return hash;
  }
}
