package com.lambdaworks.crypto;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.

import static java.lang.System.arraycopy;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * An implementation of the Password-Based Key Derivation Function as specified
 * in RFC 2898.
 *
 * @author Will Glozer
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public final class PBKDF {  // NOPMD

  private PBKDF() {
    throw new IllegalStateException("Private constructor");
  }

  /**
   * Implementation of PBKDF2 (RFC2898).
   *
   * @param alg HMAC algorithm to use.
   * @param password Password.
   * @param salt Salt.
   * @param c Iteration count.
   * @param dkLen Intended length, in octets, of the derived key.
   * @return The derived key in bytes
   * @throws GeneralSecurityException security exception
   */
  public static byte[] pbkdf2(String alg,
                              byte[] password,
                              byte[] salt,
                              int c,
                              int dkLen) throws GeneralSecurityException {
    final Mac mac = Mac.getInstance(alg);
    mac.init(new SecretKeySpec(password, alg));
    final byte[] derivedKey = new byte[dkLen];
    pbkdf2(mac, salt, c, derivedKey, dkLen);
    return derivedKey;
  }

  /**
   * Implementation of PBKDF2 (RFC2898).
   *
   * @param mac Pre-initialized {@link Mac} instance to use.
   * @param salt Salt.
   * @param c Iteration count.
   * @param derivedKey Byte array that derived key will be placed in.
   * @param dkLen Intended length, in octets, of the derived key.
   * @throws GeneralSecurityException security exception
   */
  public static void pbkdf2(Mac mac,
                            byte[] salt,
                            int c,
                            byte[] derivedKey,
                            int dkLen) throws GeneralSecurityException {
    final int lengthH = mac.getMacLength();

    if (dkLen > (Math.pow(2, 32) - 1) * lengthH) {
      throw new GeneralSecurityException("Requested key length too long");
    }
    final byte[] bytesU = new byte[lengthH];

    final byte[] bytesT = new byte[lengthH];

    final byte[] block1 = new byte[salt.length + 4];

    final int l = (int)Math.ceil((double)dkLen / lengthH);

    final int r = dkLen - (l - 1) * lengthH;

    arraycopy(salt, 0, block1, 0, salt.length);

    for (int i = 1; i <= l; i++) {
      block1[salt.length + 0] = (byte)(i >> 24 & 0xff);

      block1[salt.length + 1] = (byte)(i >> 16 & 0xff);

      block1[salt.length + 2] = (byte)(i >> 8 & 0xff);
      block1[salt.length + 3] = (byte)(i >> 0 & 0xff);

      mac.update(block1);
      mac.doFinal(bytesU, 0);
      arraycopy(bytesU, 0, bytesT, 0, lengthH);

      for (int j = 1; j < c; j++) {
        mac.update(bytesU);
        mac.doFinal(bytesU, 0);
        for (int k = 0; k < lengthH; k++) {
          bytesT[k] ^= bytesU[k];
        }
      }
      arraycopy(bytesT, 0, derivedKey, (i - 1) * lengthH, i == l ? r : lengthH);
    }
  }
}
