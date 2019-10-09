package com.javacodegeeks.abk;

import com.howtodoinjava.hashing.password.demo.bcrypt.BCryptUtil;

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
      hash = BCryptUtil.hashpw(text, BCryptUtil.gensalt(12));
      hash = hash.substring(hash.lastIndexOf('$', hash.length()) + 1);
    } catch (IllegalArgumentException e) {
      System.err.println("Illegal Argument: " + e.getMessage());
    }
    return hash;
  }
}
