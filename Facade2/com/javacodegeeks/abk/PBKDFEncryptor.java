package com.javacodegeeks.abk;

import static com.lambdaworks.codec.Base64.encode;
import static com.javacodegeeks.abk.Encrypt.getSalt;

import java.security.GeneralSecurityException;
import com.lambdaworks.crypto.PBKDF;

/**
 * Describe class <code>PBKDFEncryptor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class PBKDFEncryptor implements Encrypt {
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
      final byte[] textBytes = text.getBytes();
      final byte[] salt = getSalt();
      int iterationCount = 1000;
      int sizeKey = 64 * 8;
      byte[] derived = PBKDF.pbkdf2("HmacSHA512",
          textBytes,salt,iterationCount,sizeKey);
      hash = String.valueOf(encode(derived));
    } catch (GeneralSecurityException e) {
      System.out.println("Error generating derived key: "
          + e.getMessage());
    }
    return hash;
  }
}
