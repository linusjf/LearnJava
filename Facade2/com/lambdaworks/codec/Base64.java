package com.lambdaworks.codec;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.

import java.util.Arrays;

/**
 * High-performance base64 codec based on the algorithm used in Mikael Grev's MiG Base64. This
 * implementation is designed to handle base64 without line splitting and with optional padding.
 * Alternative character tables may be supplied to the {@code encode} and {@code decode} methods to
 * implement modified base64 schemes.
 *
 * <p>Decoding assumes correct input, the caller is responsible for ensuring that the input contains
 * no invalid characters.
 *
 * @author Will Glozer
 */
public final class Base64 { // NOPMD
  private static final char[] encode =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
  private static final int[] decode = new int[128];
  private static final char pad = '=';

  static {
    Arrays.fill(decode, -1);
    for (int i = 0; i < encode.length; i++) decode[encode[i]] = i;
    decode[pad] = 0;
  }

  private Base64() {}

  /**
   * Decode base64 chars to bytes.
   *
   * @param chars Chars to encode.
   * @return Decoded bytes.
   */
  public static byte[] decode(char[] chars) {
    return decode(chars, decode, pad);
  }

  /**
   * Decode base64 chars to bytes using the supplied decode table and padding character.
   *
   * @param src Base64 encoded data.
   * @param table Decode table.
   * @param pad Padding character.
   * @return Decoded bytes.
   */
  @SuppressWarnings("fallthrough")
  public static byte[] decode(char[] src, int[] table, char pad) {
    final int len = src.length;

    if (len == 0) return new byte[0];

    final int padCount = src[len - 1] == pad ? src[len - 2] == pad ? 2 : 1 : 0;
    final int bytes = (len * 6 >> 3) - padCount;
    final int blocks = (bytes / 3) * 3;

    final byte[] dst = new byte[bytes];
    int si = 0;
    int di = 0;
    int n;
    while (di < blocks) {
      n =
          table[src[si++]] << 18
              | table[src[si++]] << 12
              | table[src[si++]] << 6
              | table[src[si++]];
      dst[di++] = (byte) (n >> 16);
      dst[di++] = (byte) (n >> 8);
      dst[di++] = (byte) n;
    }

    if (di < bytes) {
      n = 0;
      switch (len - si) { // NOPMD
        case 4:
          n |= table[src[si + 3]]; // fall through
        case 3:
          n |= table[src[si + 2]] << 6; // fall through
        case 2:
          n |= table[src[si + 1]] << 12; // fall through
        case 1:
          n |= table[src[si]] << 18; // fall through
        default:
          break;
      }

      for (int r = 16; di < bytes; r -= 8) {
        dst[di++] = (byte) (n >> r);
      }
    }

    return dst;
  }

  /**
   * Encode bytes to base64 chars, with padding.
   *
   * @param bytes Bytes to encode.
   * @return Encoded chars.
   */
  public static char[] encode(byte[] bytes) {
    return encode(bytes, encode, pad);
  }

  /**
   * Encode bytes to base64 chars, with optional padding.
   *
   * @param bytes Bytes to encode.
   * @param padded Add padding to output.
   * @return Encoded chars.
   */
  public static char[] encode(byte[] bytes, boolean padded) {
    return encode(bytes, encode, padded ? pad : 0);
  }

  /**
   * Encode bytes to base64 chars using the supplied encode table and with optional padding.
   *
   * @param src Bytes to encode.
   * @param table Encoding table.
   * @param pad Padding character, or 0 for no padding.
   * @return Encoded chars.
   */
  public static char[] encode(byte[] src, char[] table, char pad) {
    final int len = src.length;

    if (len == 0) return new char[0];

    final int blocks = (len / 3) * 3;
    int chars = ((len - 1) / 3 + 1) << 2;
    final int tail = len - blocks;
    if (pad == 0 && tail > 0) chars -= 3 - tail;

    final char[] dst = new char[chars];
    int si = 0;
    int di = 0;
    int n;
    while (si < blocks) {
      n = (src[si++] & 0xff) << 16 | (src[si++] & 0xff) << 8 | (src[si++] & 0xff);
      dst[di++] = table[(n >>> 18) & 0x3f];
      dst[di++] = table[(n >>> 12) & 0x3f];
      dst[di++] = table[(n >>> 6) & 0x3f];
      dst[di++] = table[n & 0x3f];
    }

    if (tail > 0) {
      n = (src[si] & 0xff) << 10;
      if (tail == 2) n |= (src[++si] & 0xff) << 2;

      dst[di++] = table[(n >>> 12) & 0x3f];
      dst[di++] = table[(n >>> 6) & 0x3f];
      if (tail == 2) dst[di++] = table[n & 0x3f];

      if (pad != 0) {
        if (tail == 1) dst[di++] = pad;
        dst[di] = pad;
      }
    }
    return dst;
  }
}
