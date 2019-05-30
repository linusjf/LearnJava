package com.javacodegeeks.abk;

import static converter.ByteToHex.getHex4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Describe class <code>BCryptor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class BCryptor implements Encrypt {
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
      final MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      msgDigest.update(text.getBytes());

      final byte[] textBytes = msgDigest.digest();
      hash = getHex4(textBytes);
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Algorithm missing: " +
          e.getMessage());
    }
    return hash;
  }
}
