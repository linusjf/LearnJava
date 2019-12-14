package com.javacodegeeks.abk;

import static com.javacodegeeks.abk.Encrypt.getSalt;

import com.lambdaworks.crypto.PBKDF;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

/**
 * Describe class <code>PBKDFEncryptor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class PBKDFEncryptor implements Encrypt {
  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  @Override
  public String encrypt(String text) {
    try {
      final byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
      final byte[] salt = getSalt();
      int iterationCount = 1000;
      int sizeKey = 64 * 8;
      byte[] derived = PBKDF.pbkdf2("HmacSHA512", textBytes, salt, iterationCount, sizeKey);
      return Base64.getEncoder().encodeToString(derived);
    } catch (GeneralSecurityException e) {
      throw new AssertionError("Error generating derived key: ", e);
    }
  }
}
