package com.lambdaworks.crypto;
// Copyright (C) 2011 - Will Glozer.  All rights reserved.

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Simple {@link SCrypt} interface for hashing passwords using the <a
 * href="http://www.tarsnap.com/scrypt.html">scrypt</a> key derivation function and comparing a
 * plain text password to a hashed one. The hashed output is an extended implementation of the
 * Modular Crypt Format that also includes the scrypt algorithm parameters.
 *
 * <p>Format: <code>$s0$PARAMS$SALT$KEY</code>.
 *
 * <dl>
 *   <dd>PARAMS
 *   <dt>32-bit hex integer containing log2(N) (16 bits), r (8 bits), and p (8 bits)
 *   <dd>SALT
 *   <dt>base64-encoded salt
 *   <dd>KEY
 *   <dt>base64-encoded derived key
 * </dl>
 *
 * <code>s0</code> identifies version 0 of the scrypt format, using a 128-bit salt and 256-bit
 * derived key.
 *
 * @author Will Glozer
 */
public final class SCryptUtil { // NOPMD
  /**
   * Hash the supplied plaintext password and generate output in the format described in {@link
   * SCryptUtil}.
   *
   * @param passwd Password.
   * @param enCPUCost CPU cost parameter.
   * @param rmemCost Memory cost parameter.
   * @param p Parallelization parameter.
   * @return The hashed password.
   */
  public static String scrypt(String passwd, int enCPUCost, int rmemCost, int p) {
    try {
      final byte[] salt = new byte[16];
      SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);

      final byte[] derived =
          SCrypt.scrypt(passwd.getBytes("UTF-8"), salt, enCPUCost, rmemCost, p, 32);

      final String params = Long.toString(log2(enCPUCost) << 16L | rmemCost << 8 | p, 16);

      final StringBuilder sb = new StringBuilder((salt.length + derived.length) * 2);
      sb.append("$s0$")
          .append(params)
          .append('$')
          .append(Base64.getEncoder().encodeToString(salt))
          .append('$')
          .append(Base64.getEncoder().encodeToString(derived));

      return sb.toString();
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("JVM doesn't support UTF-8?", e);
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("JVM doesn't support SHA1PRNG or HMAC_SHA256?", e);
    }
  }

  /**
   * Compare the supplied plaintext password to a hashed password.
   *
   * @param passwd Plaintext password.
   * @param hashed scrypt hashed password.
   * @return true if passwd matches hashed value.
   */
  @SuppressWarnings("PMD.CyclomaticComplexity")
  public static boolean check(String passwd, String hashed) {
    try {
      final String[] parts = hashed.split("\\$");

      if (parts.length != 5 || !parts[1].equals("s0")) {
        throw new IllegalArgumentException("Invalid hashed value");
      }

      final long params = Long.parseLong(parts[2], 16);
      final byte[] salt = Base64.getDecoder().decode(parts[3]);
      final byte[] derived0 = Base64.getDecoder().decode(parts[4]);
      final int n = (int) Math.pow(2, params >> 16 & 0xffff);
      final int r = (int) params >> 8 & 0xff;
      final int p = (int) params & 0xff;

      final byte[] derived1 = SCrypt.scrypt(passwd.getBytes("UTF-8"), salt, n, r, p, 32);

      if (derived0.length != derived1.length)
        return false;

      int result = 0;
      for (int i = 0; i < derived0.length; i++) result |= derived0[i] ^ derived1[i];
      return result == 0;
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("JVM doesn't support UTF-8?", e);
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("JVM doesn't support SHA1PRNG or HMAC_SHA256?", e);
    }
  }

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  private static int log2(int m) {
    int log = 0;
    int n = m;
    if ((n & 0xffff0000) != 0) {
      n >>>= 16;
      log = 16;
    }
    if (n >= 256) {
      n >>>= 8;
      log += 8;
    }
    if (n >= 16) {
      n >>>= 4;
      log += 4;
    }
    if (n >= 4) {
      n >>>= 2;
      log += 2;
    }
    return log + (n >>> 1);
  }

  private SCryptUtil() {
    throw new IllegalStateException("Private constructor");
  }
}
