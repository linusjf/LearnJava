package com.javacodegeeks.abk;

import static converter.ByteToHex.getHex4;

import com.lambdaworks.crypto.SCryptUtil;

/**
 * Describe class <code>SCryptor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class SCryptor implements Encrypt {
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
      hash = SCryptUtil.scrypt(text, 16, 16, 16);
    } catch (IllegalStateException e) {
      System.err.println("Illegal state : " 
          + e.getMessage());
    }
    return hash;
  }
}
