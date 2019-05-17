package com.lambdaworks.crypto;
// Copyright (C) 2011 - Will Glozer.  All rights reserved.

import static java.lang.Integer.MAX_VALUE;
import static java.lang.System.arraycopy;

import com.lambdaworks.jni.LibraryLoader;
import com.lambdaworks.jni.LibraryLoaders;
import java.security.GeneralSecurityException;
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
public class SCrypt {
  private static final boolean native_library_loaded;

  static {
    LibraryLoader loader = LibraryLoaders.loader();
    native_library_loaded = loader.load("scrypt", true);
  }

  /**
   * Implementation of the <a href="http://www.tarsnap.com/scrypt/scrypt.pdf">scrypt KDF</a>. Calls
   * the native implementation {@link #scryptN} when the native library was successfully loaded,
   * otherwise calls {@link #scryptJ}.
   *
   * @param passwd Password.
   * @param salt Salt.
   * @param N CPU cost parameter.
   * @param r Memory cost parameter.
   * @param p Parallelization parameter.
   * @param dkLen Intended length of the derived key.
   * @return The derived key.
   * @throws GeneralSecurityException when HMAC_SHA256 is not available.
   */
  @SuppressWarnings({"parametername",
      "FormalParameterNamingConventions"})
  public static byte[] scrypt(byte[] passwd, 
      byte[] salt, 
      int N, 
      int r, 
      int p, 
      int dkLen)
      throws GeneralSecurityException {
    return native_library_loaded
        ? scryptN(passwd, salt, N, r, p, dkLen)
        : scryptJ(passwd, salt, N, r, p, dkLen);
  }

  /**
   * Native C implementation of the <a href="http://www.tarsnap.com/scrypt/scrypt.pdf">scrypt
   * KDF</a> using the code from <a
   * href="http://www.tarsnap.com/scrypt.html">http://www.tarsnap.com/scrypt.html</a>.
   *
   * @param passwd Password.
   * @param salt Salt.
   * @param N CPU cost parameter.
   * @param r Memory cost parameter.
   * @param p Parallelization parameter.
   * @param dkLen Intended length of the derived key.
   * @return The derived key.
   */
  @SuppressWarnings({"parametername", 
      "FormalParameterNamingConventions"})
  public static native byte[] scryptN(byte[] passwd, byte[] salt, int N, int r, int p, int dkLen);

  /**
   * Pure Java implementation of the <a href="http://www.tarsnap.com/scrypt/scrypt.pdf">scrypt
   * KDF</a>.
   *
   * @param passwd Password.
   * @param salt Salt.
   * @param N CPU cost parameter.
   * @param r Memory cost parameter.
   * @param p Parallelization parameter.
   * @param dkLen Intended length of the derived key.
   * @return The derived key.
   * @throws GeneralSecurityException when HMAC_SHA256 is not available.
   */
  @SuppressWarnings({"parametername",
      "FormalParameterNamingConventions"})
  public static byte[] scryptJ(byte[] passwd, byte[] salt, int N, int r, int p, int dkLen)
      throws GeneralSecurityException {
    if (N < 2 || (N & (N - 1)) != 0)
      throw new IllegalArgumentException("N must be a power of 2 greater than 1");

    if (N > MAX_VALUE / 128 / r)
      throw new IllegalArgumentException("Parameter N is too large");
    if (r > MAX_VALUE / 128 / p)
      throw new IllegalArgumentException("Parameter r is too large");

    Mac mac = Mac.getInstance("HmacSHA256");
    mac.init(new SecretKeySpec(passwd, "HmacSHA256"));

    byte[] derivedKey = new byte[dkLen];
    byte[] bytes = new byte[128 * r * p];
    byte[] xyBytes = new byte[256 * r];
    byte[] v = new byte[128 * r * N];
    int i;

    PBKDF.pbkdf2(mac, salt, 1, bytes, p * 128 * r);

    for (i = 0; i < p; i++) 
      smix(bytes, i * 128 * r, r, N, v, xyBytes);

    PBKDF.pbkdf2(mac, bytes, 1, derivedKey, dkLen);

    return derivedKey;
  }

  /**
   * Describe <code>smix</code> method here.
   *
   * @param B a <code>byte</code> value
   * @param Bi an <code>int</code> value
   * @param r an <code>int</code> value
   * @param N an <code>int</code> value
   * @param V a <code>byte</code> value
   * @param XY a <code>byte</code> value
   */
  @SuppressWarnings({"parametername",
      "FormalParameterNamingConventions"})
  public static void smix(byte[] B, int Bi, int r, int N, byte[] V, byte[] XY) {

    int xinitial = 0;
    int yinitial = 128 * r;
    int i;

    arraycopy(B, Bi, XY, xinitial, 128 * r);

    for (i = 0; i < N; i++) {
      arraycopy(XY, xinitial, V, i * (128 * r), 128 * r);
      blockmixSalsa8(XY, xinitial, yinitial, r);
    }

    for (i = 0; i < N; i++) {
      int j = integerify(XY, xinitial, r) & (N - 1);
      blockxor(V, j * (128 * r), XY, xinitial, 128 * r);
      blockmixSalsa8(XY, xinitial, yinitial, r);
    }

    arraycopy(XY, xinitial, B, Bi, 128 * r);
  }

  /**
   * Describe <code>blockmixSalsa8</code> method here.
   *
   * @param BY a <code>byte</code> value
   * @param Bi an <code>int</code> value
   * @param Yi an <code>int</code> value
   * @param r an <code>int</code> value
   */
  @SuppressWarnings("parametername")
  public static void blockmixSalsa8(byte[] BY, int Bi, int Yi, int r) {

    byte[] x = new byte[64];
    int i;

    arraycopy(BY, Bi + (2 * r - 1) * 64, x, 0, 64);

    for (i = 0; i < 2 * r; i++) {
      blockxor(BY, i * 64, x, 0, 64);
      salsa20Slash8(x);
      arraycopy(x, 0, BY, Yi + (i * 64), 64);
    }

    for (i = 0; i < r; i++) 
      arraycopy(BY, Yi + (i * 2) * 64, BY, Bi + (i * 64), 64);

    for (i = 0; i < r; i++)
      arraycopy(BY, Yi + (i * 2 + 1) * 64, BY, Bi + (i + r) * 64, 64);
  }

  /**
   * Describe <code>R</code> method here.
   *
   * @param a an <code>int</code> value
   * @param b an <code>int</code> value
   * @return an <code>int</code> value
   */
  @SuppressWarnings("methodname")
  public static int R(int a, int b) {
    return (a << b) | (a >>> (32 - b));
  }

  /**
   * Describe <code>salsa20Slash8</code> method here.
   *
   * @param bytes a <code>byte</code> value
   */
  public static void salsa20Slash8(byte[] bytes) {

    int[] b32 = new int[16];
    int[] x = new int[16];
    int i;

    for (i = 0; i < 16; i++) {
      b32[i] = (bytes[i * 4 + 0] & 0xff) << 0;
      b32[i] |= (bytes[i * 4 + 1] & 0xff) << 8;
      b32[i] |= (bytes[i * 4 + 2] & 0xff) << 16;
      b32[i] |= (bytes[i * 4 + 3] & 0xff) << 24;
    }

    arraycopy(b32, 0, x, 0, 16);

    for (i = 8; i > 0; i -= 2) {
      x[4] ^= R(x[0] + x[12], 7);
      x[8] ^= R(x[4] + x[0], 9);
      x[12] ^= R(x[8] + x[4], 13);
      x[0] ^= R(x[12] + x[8], 18);
      x[9] ^= R(x[5] + x[1], 7);
      x[13] ^= R(x[9] + x[5], 9);
      x[1] ^= R(x[13] + x[9], 13);
      x[5] ^= R(x[1] + x[13], 18);
      x[14] ^= R(x[10] + x[6], 7);
      x[2] ^= R(x[14] + x[10], 9);
      x[6] ^= R(x[2] + x[14], 13);
      x[10] ^= R(x[6] + x[2], 18);
      x[3] ^= R(x[15] + x[11], 7);
      x[7] ^= R(x[3] + x[15], 9);
      x[11] ^= R(x[7] + x[3], 13);
      x[15] ^= R(x[11] + x[7], 18);
      x[1] ^= R(x[0] + x[3], 7);
      x[2] ^= R(x[1] + x[0], 9);
      x[3] ^= R(x[2] + x[1], 13);
      x[0] ^= R(x[3] + x[2], 18);
      x[6] ^= R(x[5] + x[4], 7);
      x[7] ^= R(x[6] + x[5], 9);
      x[4] ^= R(x[7] + x[6], 13);
      x[5] ^= R(x[4] + x[7], 18);
      x[11] ^= R(x[10] + x[9], 7);
      x[8] ^= R(x[11] + x[10], 9);
      x[9] ^= R(x[8] + x[11], 13);
      x[10] ^= R(x[9] + x[8], 18);
      x[12] ^= R(x[15] + x[14], 7);
      x[13] ^= R(x[12] + x[15], 9);
      x[14] ^= R(x[13] + x[12], 13);
      x[15] ^= R(x[14] + x[13], 18);
    }

    for (i = 0; i < 16; ++i)
      b32[i] = x[i] + b32[i];

    for (i = 0; i < 16; i++) {
      bytes[i * 4 + 0] = (byte) (b32[i] >> 0 & 0xff);
      bytes[i * 4 + 1] = (byte) (b32[i] >> 8 & 0xff);
      bytes[i * 4 + 2] = (byte) (b32[i] >> 16 & 0xff);
      bytes[i * 4 + 3] = (byte) (b32[i] >> 24 & 0xff);
    }
  }

  /**
   * Describe <code>blockxor</code> method here.
   *
   * @param S a <code>byte</code> value
   * @param Si an <code>int</code> value
   * @param D a <code>byte</code> value
   * @param Di an <code>int</code> value
   * @param len an <code>int</code> value
   */
  @SuppressWarnings("parametername")
  public static void blockxor(byte[] S, int Si, byte[] D, int Di, int len) {
    for (int i = 0; i < len; i++) D[Di + i] ^= S[Si + i];
  }

  /**
   * Describe <code>integerify</code> method here.
   *
   * @param B a <code>byte</code> value
   * @param Bi an <code>int</code> value
   * @param r an <code>int</code> value
   * @return an <code>int</code> value
   */
  @SuppressWarnings("parametername")
  public static int integerify(byte[] B, int Bi, int r) {
    int n;

    Bi += (2 * r - 1) * 64;

    n = (B[Bi + 0] & 0xff) << 0;
    n |= (B[Bi + 1] & 0xff) << 8;
    n |= (B[Bi + 2] & 0xff) << 16;
    n |= (B[Bi + 3] & 0xff) << 24;

    return n;
  }
}
