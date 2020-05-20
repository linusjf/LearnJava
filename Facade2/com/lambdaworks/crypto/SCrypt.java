package com.lambdaworks.crypto;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.
import static java.lang.Integer.MAX_VALUE;
import static java.lang.System.arraycopy;

import com.lambdaworks.jni.LibraryLoader;
import com.lambdaworks.jni.LibraryLoaders;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * An implementation of the <a href="http://www.tarsnap.com/scrypt/scrypt.pdf">scrypt</a> key
 * derivation function. This class will attempt to load a native library containing the optimized C
 * implementation from <a
 * href="http://www.tarsnap.com/scrypt.html">http://www.tarsnap.com/scrypt.html</a> and fall back to
 * the pure Java version if that fails.
 *
 * @author Will Glozer
 */
public final class SCrypt {  // NOPMD
  private static final boolean NATIVE_LIBRARY_LOADED;

  static {
    final LibraryLoader loader = LibraryLoaders.loader();
    NATIVE_LIBRARY_LOADED = loader.load("scrypt", true);
  }

  private SCrypt() {
    throw new IllegalStateException("Private constructor");
  }

  /**
   * Implementation of the <a href="http://www.tarsnap.com/scrypt/scrypt.pdf">scrypt KDF</a>. Calls
   * the native implementation {@link #scryptN} when the native library was successfully loaded,
   * otherwise calls {@link #scryptJ}.
   *
   * @param passwd Password.
   * @param salt Salt.
   * @param enCPUCost CPU cost parameter.
   * @param r Memory cost parameter.
   * @param p Parallelization parameter.
   * @param dkLen Intended length of the derived key.
   * @return The derived key.
   * @throws GeneralSecurityException when HMAC_SHA256 is not available.
   */
  public static byte[] scrypt(byte[] passwd,
                              byte[] salt,
                              int enCPUCost,
                              int r,
                              int p,
                              int dkLen) throws GeneralSecurityException {
    byte[] derived = NATIVE_LIBRARY_LOADED
                         ? scryptN(passwd, salt, enCPUCost, r, p, dkLen)
                         : scryptJ(passwd, salt, enCPUCost, r, p, dkLen);
    return Arrays.copyOf(derived, derived.length);
  }

  /**
   * Native C implementation of the <a href="http://www.tarsnap.com/scrypt/scrypt.pdf">scrypt
   * KDF</a> using the code from <a
   * href="http://www.tarsnap.com/scrypt.html">http://www.tarsnap.com/scrypt.html</a>.
   *
   * @param passwd Password.
   * @param salt Salt.
   * @param enCPUCost CPU cost parameter.
   * @param r Memory cost parameter.
   * @param p Parallelization parameter.
   * @param dkLen Intended length of the derived key.
   * @return The derived key.
   */
  @SuppressWarnings("checkstyle:IllegalToken")
  public static native byte[] scryptN(byte[] passwd,
                                      byte[] salt,
                                      int enCPUCost,
                                      int r,
                                      int p,
                                      int dkLen);

  /**
   * Pure Java implementation of the <a href="http://www.tarsnap.com/scrypt/scrypt.pdf">scrypt
   * KDF</a>.
   *
   * @param passwd Password.
   * @param salt Salt.
   * @param enCPUCost CPU cost parameter.
   * @param r Memory cost parameter.
   * @param p Parallelization parameter.
   * @param dkLen Intended length of the derived key.
   * @return The derived key.
   * @throws GeneralSecurityException when HMAC_SHA256 is not available.
   */
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static byte[] scryptJ(byte[] passwd,
                               byte[] salt,
                               int enCPUCost,
                               int r,
                               int p,
                               int dkLen) throws GeneralSecurityException {
    if (enCPUCost < 2 || (enCPUCost & (enCPUCost - 1)) != 0)
      throw new IllegalArgumentException(
          "enCPUCost must be a power of 2 greater than 1");

    if (enCPUCost > MAX_VALUE / 128 / r)
      throw new IllegalArgumentException("Parameter nCPUCost is too large");
    if (r > MAX_VALUE / 128 / p)
      throw new IllegalArgumentException("Parameter r is too large");

    final Mac mac = Mac.getInstance("HmacSHA256");
    mac.init(new SecretKeySpec(passwd, "HmacSHA256"));

    final byte[] derivedKey = new byte[dkLen];
    final byte[] bytes = new byte[128 * r * p];
    final byte[] xyBytes = new byte[256 * r];
    final byte[] v = new byte[128 * r * enCPUCost];
    int i;

    PBKDF.pbkdf2(mac, salt, 1, bytes, p * 128 * r);

    for (i = 0; i < p; i++)
      smix(bytes, i * 128 * r, r, enCPUCost, v, xyBytes);

    PBKDF.pbkdf2(mac, bytes, 1, derivedKey, dkLen);

    return derivedKey;
  }

  /**
   * Describe <code>smix</code> method here.
   *
   * @param bytes a <code>byte</code> value
   * @param initialB an <code>int</code> value
   * @param r an <code>int</code> value
   * @param n an <code>int</code> value
   * @param v a <code>byte</code> value
   * @param xy a <code>byte</code> value
   */
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void smix(byte[] bytes,
                          int initialB,
                          int r,
                          int n,
                          byte[] v,
                          byte[] xy) {
    int xinitial = 0;
    final int yinitial = 128 * r;
    int i;

    arraycopy(bytes, initialB, xy, xinitial, 128 * r);

    for (i = 0; i < n; i++) {
      arraycopy(xy, xinitial, v, i * (128 * r), 128 * r);
      blockmixSalsa8(xy, xinitial, yinitial, r);
    }

    int j;
    for (i = 0; i < n; i++) {
      j = integerify(xy, xinitial, r) & (n - 1);
      blockXOR(v, j * (128 * r), xy, xinitial, 128 * r);
      blockmixSalsa8(xy, xinitial, yinitial, r);
    }

    arraycopy(xy, xinitial, bytes, initialB, 128 * r);
  }

  /**
   * Describe <code>blockmixSalsa8</code> method here.
   *
   * @param bytes a <code>byte</code> value
   * @param initialB an <code>int</code> value
   * @param initialY an <code>int</code> value
   * @param r an <code>int</code> value
   */
  public static void blockmixSalsa8(byte[] bytes,
                                    int initialB,
                                    int initialY,
                                    int r) {
    final byte[] x = new byte[64];
    int i;

    arraycopy(bytes, initialB + (2 * r - 1) * 64, x, 0, 64);

    for (i = 0; i < 2 * r; i++) {
      blockXOR(bytes, i * 64, x, 0, 64);
      salsa20Slash8(x);
      arraycopy(x, 0, bytes, initialY + (i * 64), 64);
    }

    for (i = 0; i < r; i++)
      arraycopy(bytes, initialY + (i * 2) * 64, bytes, initialB + (i * 64), 64);

    for (i = 0; i < r; i++)
      arraycopy(bytes,
                initialY + (i * 2 + 1) * 64,
                bytes,
                initialB + (i + r) * 64,
                64);
  }

  /**
   * Describe <code>leftRotate</code> method here.
   *
   * @param a an <code>int</code> value
   * @param b an <code>int</code> value
   * @return an <code>int</code> value
   */
  public static int leftRotate(int a, int b) {
    return (a << b) | (a >>> (32 - b));
  }

  /**
   * Describe <code>salsa20Slash8</code> method here.
   *
   * @param bytes a <code>byte</code> value
   */
  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void salsa20Slash8(byte[] bytes) {
    final int[] b32 = new int[16];
    final int[] x = new int[16];
    int i;

    for (i = 0; i < 16; i++) {
      b32[i] = (bytes[i * 4 + 0] & 0xff) << 0;
      b32[i] |= (bytes[i * 4 + 1] & 0xff) << 8;
      b32[i] |= (bytes[i * 4 + 2] & 0xff) << 16;
      b32[i] |= (bytes[i * 4 + 3] & 0xff) << 24;
    }

    arraycopy(b32, 0, x, 0, 16);

    for (i = 8; i > 0; i -= 2) {
      x[4] ^= leftRotate(x[0] + x[12], 7);
      x[8] ^= leftRotate(x[4] + x[0], 9);
      x[12] ^= leftRotate(x[8] + x[4], 13);
      x[0] ^= leftRotate(x[12] + x[8], 18);
      x[9] ^= leftRotate(x[5] + x[1], 7);
      x[13] ^= leftRotate(x[9] + x[5], 9);
      x[1] ^= leftRotate(x[13] + x[9], 13);
      x[5] ^= leftRotate(x[1] + x[13], 18);
      x[14] ^= leftRotate(x[10] + x[6], 7);
      x[2] ^= leftRotate(x[14] + x[10], 9);
      x[6] ^= leftRotate(x[2] + x[14], 13);
      x[10] ^= leftRotate(x[6] + x[2], 18);
      x[3] ^= leftRotate(x[15] + x[11], 7);
      x[7] ^= leftRotate(x[3] + x[15], 9);
      x[11] ^= leftRotate(x[7] + x[3], 13);
      x[15] ^= leftRotate(x[11] + x[7], 18);
      x[1] ^= leftRotate(x[0] + x[3], 7);
      x[2] ^= leftRotate(x[1] + x[0], 9);
      x[3] ^= leftRotate(x[2] + x[1], 13);
      x[0] ^= leftRotate(x[3] + x[2], 18);
      x[6] ^= leftRotate(x[5] + x[4], 7);
      x[7] ^= leftRotate(x[6] + x[5], 9);
      x[4] ^= leftRotate(x[7] + x[6], 13);
      x[5] ^= leftRotate(x[4] + x[7], 18);
      x[11] ^= leftRotate(x[10] + x[9], 7);
      x[8] ^= leftRotate(x[11] + x[10], 9);
      x[9] ^= leftRotate(x[8] + x[11], 13);
      x[10] ^= leftRotate(x[9] + x[8], 18);
      x[12] ^= leftRotate(x[15] + x[14], 7);
      x[13] ^= leftRotate(x[12] + x[15], 9);
      x[14] ^= leftRotate(x[13] + x[12], 13);
      x[15] ^= leftRotate(x[14] + x[13], 18);
    }

    for (i = 0; i < 16; ++i)
      b32[i] = x[i] + b32[i];

    for (i = 0; i < 16; i++) {
      bytes[i * 4 + 0] = (byte)(b32[i] >> 0 & 0xff);
      bytes[i * 4 + 1] = (byte)(b32[i] >> 8 & 0xff);
      bytes[i * 4 + 2] = (byte)(b32[i] >> 16 & 0xff);
      bytes[i * 4 + 3] = (byte)(b32[i] >> 24 & 0xff);
    }
  }

  /**
   * Describe <code>blockXOR</code> method here.
   *
   * @param s a <code>byte</code> value
   * @param sinitial an <code>int</code> value
   * @param d a <code>byte</code> value
   * @param dinitial an <code>int</code> value
   * @param len an <code>int</code> value
   */
  public static void blockXOR(byte[] s,
                              int sinitial,
                              byte[] d,
                              int dinitial,
                              int len) {
    for (int i = 0; i < len; i++)
      s[dinitial + i] ^= s[sinitial + i];
  }

  /**
   * Describe <code>integerify</code> method here.
   *
   * @param b a <code>byte</code> value
   * @param initB an <code>int</code> value
   * @param r an <code>int</code> value
   * @return an <code>int</code> value
   */
  public static int integerify(byte[] b, int initB, int r) {
    int n;

    int initialB = initB;
    initialB += (2 * r - 1) * 64;

    n = (b[initialB + 0] & 0xff) << 0;
    n |= (b[initialB + 1] & 0xff) << 8;
    n |= (b[initialB + 2] & 0xff) << 16;
    n |= (b[initialB + 3] & 0xff) << 24;

    return n;
  }
}
