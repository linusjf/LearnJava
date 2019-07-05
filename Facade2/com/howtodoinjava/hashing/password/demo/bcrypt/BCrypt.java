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
import static com.howtodoinjava.hashing.password.demo.bcrypt.BCryptUtil.streamtoword;

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
  @SuppressWarnings("membername") private int[] P; // NOPMD

  @SuppressWarnings("membername") private int[] S; // NOPMD

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

    for (i = 0; i < plen; i++) P[i] = P[i] ^ streamtoword(key, koffp);

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

    for (i = 0; i < plen; i++) P[i] = P[i] ^ streamtoword(key, koffp);

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
  byte[] cryptRaw(final byte[] password, final byte[] salt, final int logRounds) {
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
      for (j = 0; j < (clen >> 1); j++) encipher(cdata, j << 1);
    }

    final byte[] ret = new byte[clen * 4];
    for (i = 0, j = 0; i < clen; i++) {
      ret[j++] = (byte) ((cdata[i] >> 24) & 0xff);
      ret[j++] = (byte) ((cdata[i] >> 16) & 0xff);
      ret[j++] = (byte) ((cdata[i] >> 8) & 0xff);
      ret[j++] = (byte) (cdata[i] & 0xff);
    }
    return ret;
  }
}
