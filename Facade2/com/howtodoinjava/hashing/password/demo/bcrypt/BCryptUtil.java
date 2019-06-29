package com.howtodoinjava.hashing.password.demo.bcrypt;

// Copyright (c) 2006 Damien Miller <djm@mindrot.org>
//
// Permission to use, copy, modify, and distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
// ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
// OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

import static com.howtodoinjava.hashing.password.demo.bcrypt.BCryptConstants.*;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BCryptUtil {

  private static final Pattern PASSWORD_PATTERN =
      Pattern.compile("^((\\$2(a){0,1}\\$){1})(.*)");

  private BCryptUtil() {
    throw new IllegalStateException("Private constructor");
  }

  /**
   * Encode a byte array using bcrypt's slightly-modified base64 encoding
   * scheme. Note that this is *not* compatible with the standard MIME-base64
   * encoding.
   *
   * @param d the byte array to encode
   * @return base64-encoded string
   * @exception IllegalArgumentException if the length is invalid
   */
  private static String encodeBase64(final byte[] d) {
    return Base64.getEncoder().encodeToString(d);
  }

  /**
   * Decode a string encoded using bcrypt's base64 scheme to a byte array. Note
   * that this is *not* compatible with the standard MIME-base64 encoding.
   *
   * @param s the string to decode
   * @return an array containing the decoded bytes
   * @throws IllegalArgumentException if maxolen is invalid
   */
  private static byte[] decodeBase64(final String s) {
    return Base64.getDecoder().decode(s);
  }

  /**
   * Cylically extract a word of key material.
   *
   * @param data the string to extract the data from
   * @param offp a "pointer" (as a one-entry array) to the current offset into
   *     data
   * @return the next word of material from data
   */
  @SuppressWarnings("PMD.UseVarargs")
  static int streamtoword(final byte[] data, final int[] offp) {
    int i;
    int word = 0;
    int off = offp[0];

    for (i = 0; i < 4; i++) {
      word = (word << 8) | (data[off] & 0xff);
      off = (off + 1) % data.length;
    }

    offp[0] = off;
    return word;
  }

  /**
   * Using regex. Match the initial salt and match the minor and offset values.
   */
  private static String[] retrieveOffsetMinor(String salt) {
    char minor = (char)0;
    int off = 0;
    Matcher matcher = PASSWORD_PATTERN.matcher(salt);
    if (matcher.matches()) {
      off = matcher.end(1);
      if (off == OFFSET_4)
        minor = 'a';
    } else
      throw new IllegalArgumentException("Invalid salt:" + salt);
    // Extract number of rounds
    if (salt.charAt(off + 2) > DOLLAR)
      throw new IllegalArgumentException("Missing salt rounds");
    return new String[] {String.valueOf(minor), String.valueOf(off)};
  }

  /**
   * Hash a password using the OpenBSD bcrypt scheme.
   *
   * @param password the password to hash
   * @param salt the salt to hash with (perhaps generated using BCrypt.gensalt)
   * @return the hashed password
   */
  public static String hashpw(final String password, final String salt) {
    String[] vals = retrieveOffsetMinor(salt);

    char minor = vals[0].charAt(0);

    byte[] passwordb;
    try {
      passwordb =
          (password + (minor >= LOWER_CASE_A ? "\000" : "")).getBytes("UTF-8");
    } catch (final UnsupportedEncodingException uee) {
      throw new AssertionError("UTF-8 is not supported", uee);
    }

    int off = Integer.parseInt(vals[1]);
    final int rounds = Integer.parseInt(salt.substring(off, off + 2));

    String realSalt = salt.substring(off + 3, off + 25);
    final byte[] saltb = decodeBase64(realSalt);
    final BCrypt crypt = new BCrypt();
    final byte[] hashed = crypt.cryptRaw(passwordb, saltb, rounds);
    return getHashedPassword(minor, rounds, saltb, hashed);
  }

  private static String getHashedPassword(char minor,
                                          int rounds,
                                          byte[] saltb,
                                          byte[] hashed) {
    final StringBuilder rs = new StringBuilder();
    rs.append("$2");
    if (minor >= LOWER_CASE_A)
      rs.append(minor);
    rs.append(DOLLAR);
    if (rounds < TEN)
      rs.append('0');
    rs.append(Integer.toString(rounds))
        .append(DOLLAR)
        .append(encodeBase64(saltb))
        .append(DOLLAR)
        .append(encodeBase64(hashed));
    return rs.toString();
  }

  /**
   * Generate a salt for use with the BCrypt.hashpw() method
   *
   * @param logRounds the log2 of the number of rounds of hashing to apply - the
   *     work factor therefore increases as 2**log_rounds.
   * @param random an instance of SecureRandom to use
   * @return an encoded salt value
   */
  public static String gensalt(final int logRounds, final SecureRandom random) {
    final StringBuilder rs = new StringBuilder();
    final byte[] rnd = new byte[BCRYPT_SALT_LEN];

    random.nextBytes(rnd);
    rs.append("$2a$");
    if (logRounds < TEN)
      rs.append('0');
    rs.append(Integer.toString(logRounds))
        .append(DOLLAR)
        .append(encodeBase64(rnd));
    return rs.toString();
  }

  /**
   * Generate a salt for use with the BCrypt.hashpw() method
   *
   * @param logRounds the log2 of the number of rounds of hashing to apply - the
   *     work factor therefore increases as 2**log_rounds.
   * @return an encoded salt value
   */
  public static String gensalt(final int logRounds) {
    return gensalt(logRounds, new SecureRandom());
  }

  /**
   * Generate a salt for use with the BCrypt.hashpw() method, selecting a
   * reasonable default for the number of hashing rounds to apply.
   *
   * @return an encoded salt value
   */
  public static String gensalt() {
    return gensalt(GENSALT_DEFAULT_LOG2_ROUNDS);
  }

  /**
   * Check that a plaintext password matches a previously hashed one.
   *
   * @param plaintext the plaintext password to verify
   * @param hashed the previously-hashed password
   * @return true if the passwords match, false otherwise
   */
  public static boolean checkpw(final String plaintext, final String hashed) {
    return hashed.compareTo(hashpw(plaintext, hashed)) == 0;
  }
}
