package com.javacodegeeks.abk;

import static com.lambdaworks.codec.Base64.encode;

import com.howtodoinjava.hashing.password.demo.bcrypt.BCrypt;

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
      hash = BCrypt.hashpw(text, 
          BCrypt.gensalt(12));
    } catch (IllegalArgumentException e) {
      System.err.println("Illegal Argument: " +
          e.getMessage());
    }
    return hash;
  }
}
