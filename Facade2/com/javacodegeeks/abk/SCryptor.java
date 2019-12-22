package com.javacodegeeks.abk;

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
  @SuppressWarnings("PMD.LawOfDemeter")
  public String encrypt(String text) {
    try {
      String hash = SCryptUtil.scrypt(text, 16, 16, 16);
      return hash.substring(
          hash.lastIndexOf('$', hash.length()) + 1);
    } catch (IllegalStateException e) {
      throw new AssertionError("Illegal state : ", e);
    }
  }
}
