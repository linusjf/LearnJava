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

/**
 * BCrypt implements OpenBSD-style Blowfish password hashing using the scheme
 * described in "A Future-Adaptable Password Scheme" by Niels Provos and David
 * Mazieres.
 *
 * <p>This password hashing system tries to thwart off-line password cracking
 * using a computationally-intensive hashing algorithm, based on Bruce
 * Schneier's Blowfish cipher. The work factor of the algorithm is
 * parameterised, so it can be increased as computers get faster.
 *
 * <p>Usage is really simple. To hash a password for the first time, call the
 * hashpw method with a random salt, like this:
 *
 * <p><code>
 * String pw_hash = BCrypt.hashpw(plain_password, BCrypt.gensalt());
 *
 * </code>
 *
 * <p>To check whether a plaintext password matches one that has been hashed
 * previously, use the checkpw method:
 *
 * <p><code>
 * if (BCrypt.checkpw(candidate_password, stored_hash))
 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println("It matches");
 * else
 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println("It does not match");
 * </code>
 *
 * <p>The gensalt() method takes an optional parameter (log_rounds) that
 * determines the computational complexity of the hashing:
 *
 * <p><code>
 * String strong_salt = BCrypt.gensalt(10)
 * String stronger_salt = BCrypt.gensalt(12)
 * </code>
 *
 * <p>The amount of work increases exponentially (2**log_rounds), so each
 * increment is twice as much work. The default log_rounds is 10, and the valid
 * range is 4 to 31.
 *
 * @author Damien Miller
 * @version 0.2
 */
public class BCrypt {
  // Expanded Blowfish key
  @SuppressWarnings("membername") private int[] P;  // NOPMD

  @SuppressWarnings("membername") private int[] S;  // NOPMD

  /**
   * Encode a byte array using bcrypt's slightly-modified base64 encoding
   * scheme. Note that this is *not* compatible with the standard MIME-base64
   * encoding.
   *
   * @param d the byte array to encode
   * @param len the number of bytes to encode
   * @return base64-encoded string
   * @exception IllegalArgumentException if the length is invalid
   */
  private static String encodeBase64(final byte[] d, final int len) {
    int off = 0;
    final StringBuilder rs = new StringBuilder();
    int c1;
    int c2;

    if (len <= 0 || len > d.length)
      throw new IllegalArgumentException("Invalid len");

    while (off < len) {
      c1 = d[off++] & 0xff;
      rs.append(BASE64CODE[(c1 >> 2) & 0x3f]);
      c1 = (c1 & 0x03) << 4;
      if (off >= len) {
        rs.append(BASE64CODE[c1 & 0x3f]);
        break;
      }
      c2 = d[off++] & 0xff;
      c1 |= (c2 >> 4) & 0x0f;
      rs.append(BASE64CODE[c1 & 0x3f]);
      c1 = (c2 & 0x0f) << 2;

      if (off >= len) {
        rs.append(BASE64CODE[c1 & 0x3f]);
        break;
      }

      c2 = d[off++] & 0xff;
      c1 |= (c2 >> 6) & 0x03;
      rs.append(BASE64CODE[c1 & 0x3f]);
      rs.append(BASE64CODE[c2 & 0x3f]);
    }
    return rs.toString();
  }

  /**
   * Look up the 3 bits base64-encoded by the specified character,
   * range-checking againt conversion table.
   *
   * @param x the base64-encoded value
   * @return the decoded value of x
   */
  private static byte char64(final char x) {
    if ((int)x < 0 || (int)x > INDEX64.length)
      return -1;
    return INDEX64[(int)x];
  }

  /**
   * Decode a string encoded using bcrypt's base64 scheme to a byte array. Note
   * that this is *not* compatible with the standard MIME-base64 encoding.
   *
   * @param s the string to decode
   * @param maxolen the maximum number of bytes to decode
   * @return an array containing the decoded bytes
   * @throws IllegalArgumentException if maxolen is invalid
   */
  @SuppressWarnings({"checkstyle:cyclomaticcomplexity",
                     "checkstyle:npathcomplexity", "PMD.CyclomaticComplexity"})
  private static byte[] decodeBase64(final String s, final int maxolen) {
    checkMaxLengthParameter(maxolen);

    final StringBuilder rs = new StringBuilder();
    int off = 0;
    final int slen = s.length();
    int olen = 0;
    byte c1;
    byte c2;
    byte c3;
    byte c4;
    byte o;

    while (off < slen - 1 && olen < maxolen) {
      c1 = char64(s.charAt(off++));
      c2 = char64(s.charAt(off++));
      if (c1 == -1 || c2 == -1)
        break;
      o = (byte)(c1 << 2);
      o |= (c2 & 0x30) >> 4;
      rs.append((char)o);
      if (++olen >= maxolen || off >= slen)
        break;
      c3 = char64(s.charAt(off++));
      if (c3 == -1)
        break;
      o = (byte)((c2 & 0x0f) << 4);
      o |= (c3 & 0x3c) >> 2;
      rs.append((char)o);
      if (++olen >= maxolen || off >= slen)
        break;
      c4 = char64(s.charAt(off++));
      o = (byte)((c3 & 0x03) << 6);
      o |= c4;
      rs.append((char)o);
      ++olen;
    }
    return convertToBytes(rs, olen);
  }

  private static void checkMaxLengthParameter(int maxolen) {
    if (maxolen <= 0)
      throw new IllegalArgumentException("Invalid maxolen");
  }

  private static byte[] convertToBytes(StringBuilder rs, int olen) {
    final byte[] ret = new byte[olen];
    for (int off = 0; off < olen; off++)
      ret[off] = (byte)rs.charAt(off);
    return ret;
  }

  /**
   * Blowfish encipher a single 64-bit block encoded as two 32-bit halves.
   *
   * @param lr an array containing the two 32-bit half blocks
   * @param off the position in the array of the blocks
   */
  private void encipher(final int[] lr, final int off) {
    int i;
    int n;
    int l = lr[off];
    int r = lr[off + 1];

    l ^= P[0];
    for (i = 0; i <= BLOWFISH_NUM_ROUNDS - 2;) {
      // Feistel substitution on left word
      n = S[(l >> 24) & 0xff];
      n += S[0x100 | ((l >> 16) & 0xff)];
      n ^= S[0x200 | ((l >> 8) & 0xff)];
      n += S[0x300 | (l & 0xff)];
      r ^= n ^ P[++i];

      // Feistel substitution on right word
      n = S[(r >> 24) & 0xff];
      n += S[0x100 | ((r >> 16) & 0xff)];
      n ^= S[0x200 | ((r >> 8) & 0xff)];
      n += S[0x300 | (r & 0xff)];
      l ^= n ^ P[++i];
    }
    lr[off] = r ^ P[BLOWFISH_NUM_ROUNDS + 1];
    lr[off + 1] = l;
  }

  /**
   * Cycically extract a word of key material.
   *
   * @param data the string to extract the data from
   * @param offp a "pointer" (as a one-entry array) to the current offset into
   *     data
   * @return the next word of material from data
   */
  @SuppressWarnings("PMD.UseVarargs")
  private static int streamtoword(final byte[] data, final int[] offp) {
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

  /** Initialise the Blowfish key schedule. */
  private void initKey() {
    P = ORIGP.clone();
    S = ORIGS.clone();
  }

  /**
   * Key the Blowfish cipher.
   *
   * @param key an array containing the key
   */
  private void key(final byte[] key) {
    int i;
    final int[] koffp = {0};
    final int[] lr = {0, 0};
    final int plen = P.length;
    final int slen = S.length;

    for (i = 0; i < plen; i++)
      P[i] = P[i] ^ streamtoword(key, koffp);

    for (i = 0; i < plen; i += 2) {
      encipher(lr, 0);
      P[i] = lr[0];
      P[i + 1] = lr[1];
    }

    for (i = 0; i < slen; i += 2) {
      encipher(lr, 0);
      S[i] = lr[0];
      S[i + 1] = lr[1];
    }
  }

  /**
   * Perform the "enhanced key schedule" step described by Provos and Mazieres
   * in "A Future-Adaptable Password Scheme"
   * http://www.openbsd.org/papers/bcrypt-paper.ps.
   *
   * @param data salt information
   * @param key password information
   */
  private void ekskey(final byte[] data, final byte[] key) {
    int i;
    final int[] koffp = {0};
    final int[] doffp = {0};
    final int[] lr = {0, 0};
    final int plen = P.length;
    final int slen = S.length;

    for (i = 0; i < plen; i++)
      P[i] = P[i] ^ streamtoword(key, koffp);

    for (i = 0; i < plen; i += 2) {
      lr[0] ^= streamtoword(data, doffp);
      lr[1] ^= streamtoword(data, doffp);
      encipher(lr, 0);
      P[i] = lr[0];
      P[i + 1] = lr[1];
    }

    for (i = 0; i < slen; i += 2) {
      lr[0] ^= streamtoword(data, doffp);
      lr[1] ^= streamtoword(data, doffp);
      encipher(lr, 0);
      S[i] = lr[0];
      S[i + 1] = lr[1];
    }
  }

  private void checkCryptParameters(int logRounds, byte[] salt) {
    if (logRounds < 4 || logRounds > 31)
      throw new IllegalArgumentException("Bad number of rounds");
    if (salt.length != BCRYPT_SALT_LEN)
      throw new IllegalArgumentException("Bad salt length");
  }

  /**
   * Perform the central password hashing step in the bcrypt scheme.
   *
   * @param password the password to hash
   * @param salt the binary salt to hash with the password
   * @param logRounds the binary logarithm of the number of rounds of hashing to
   *     apply
   * @return an array containing the binary hashed password
   */
  private byte[] cryptRaw(final byte[] password, final byte[] salt,
                          final int logRounds) {
    checkCryptParameters(logRounds, salt);
    final int[] cdata = BFCRYPTCIPHERTEXT.clone();
    final int clen = cdata.length;

    final int rounds = 1 << logRounds;
    initKey();
    ekskey(salt, password);
    int i;
    for (i = 0; i < rounds; i++) {
      key(password);
      key(salt);
    }
    int j;
    for (i = 0; i < 64; i++) {
      for (j = 0; j < (clen >> 1); j++)
        encipher(cdata, j << 1);
    }

    final byte[] ret = new byte[clen * 4];
    for (i = 0, j = 0; i < clen; i++) {
      ret[j++] = (byte)((cdata[i] >> 24) & 0xff);
      ret[j++] = (byte)((cdata[i] >> 16) & 0xff);
      ret[j++] = (byte)((cdata[i] >> 8) & 0xff);
      ret[j++] = (byte)(cdata[i] & 0xff);
    }
    return ret;
  }

  /**
   * Consider using regex. Match the initial salt and match the minor and
   * offset values.
   */
  private static String[] retrieveOffsetMinor(String salt) {
    char minor = (char)0;
    int off = 0;

    if (salt.charAt(0) != DOLLAR || salt.charAt(1) != '2')
      throw new IllegalArgumentException("Invalid salt version");
    if (salt.charAt(2) == DOLLAR)
      off = 3;
    else {
      minor = salt.charAt(2);
      if (minor != LOWER_CASE_A || salt.charAt(3) != DOLLAR)
        throw new IllegalArgumentException("Invalid salt revision");
      off = 4;
    }

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
    int off = Integer.parseInt(vals[1]);

    byte[] passwordb;
    final int rounds = Integer.parseInt(salt.substring(off, off + 2));

    String realSalt = salt.substring(off + 3, off + 25);
    try {
      passwordb =
          (password + (minor >= LOWER_CASE_A ? "\000" : "")).getBytes("UTF-8");
    } catch (final UnsupportedEncodingException uee) {
      throw new AssertionError("UTF-8 is not supported", uee);
    }

    final byte[] saltb = decodeBase64(realSalt, BCRYPT_SALT_LEN);
    final BCrypt crypt = new BCrypt();
    final byte[] hashed = crypt.cryptRaw(passwordb, saltb, rounds);
    return getHashedPassword(minor, rounds, saltb, hashed);
  }

  private static String getHashedPassword(char minor, int rounds, byte[] saltb,
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
        .append(encodeBase64(saltb, saltb.length))
        .append(DOLLAR)
        .append(encodeBase64(hashed, BFCRYPTCIPHERTEXT.length * 4 - 1));
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
        .append(encodeBase64(rnd, rnd.length));
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
