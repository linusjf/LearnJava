package com.javacodegeeks.abk;

import static com.howtodoinjava.hashing.password.demo.bcrypt.BCryptUtil.*;

import java.util.logging.Logger;

/**
 * Describe class <code>BCryptor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class BCryptor implements Encrypt {
  private static final Logger LOGGER =
      Logger.getLogger(BCryptor.class.getName());

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
      String hash = hashpw(text, gensalt(12));
      hash = hash.substring(hash.lastIndexOf('$', hash.length()) + 1);
      return hash;
    } catch (IllegalArgumentException e) {
      LOGGER.severe(
          () -> String.format("Illegal Argument: %s", e.getMessage()));
      throw new AssertionError("Illegal argument: " + text, e);
    }
  }
}
