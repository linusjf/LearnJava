package com.javacodegeeks.abk;

import static converter.ByteToHex.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
  public String encrypt(String text) {
    String hash = "";
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-384");
      byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));

      hash = getHex4(textBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
