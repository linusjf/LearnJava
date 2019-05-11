package com.javacodegeeks.facade;

import com.javacodegeeks.abk.Encrypt;
import com.javacodegeeks.abk.MD5Encryptor;
import com.javacodegeeks.abk.MD5Salted;
import com.javacodegeeks.abk.SHA;
import com.javacodegeeks.abk.SHA224;
import com.javacodegeeks.abk.SHA224Salted;
import com.javacodegeeks.abk.SHA256;
import com.javacodegeeks.abk.SHA256Salted;
import com.javacodegeeks.abk.SHA384;
import com.javacodegeeks.abk.SHA384Salted;
import com.javacodegeeks.abk.SHA512;
import com.javacodegeeks.abk.SHA512Salted;
import com.javacodegeeks.abk.SHASalted;

/**
 * Describe class <code>EncryptorFacade</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class EncryptorFacade {

  public static enum EncryptionType {
    MD5,
    MD5Salted,
    SHA,
    SHASalted,
    SHA224,
    SHA224Salted,
    SHA256,
    SHA256Salted,
    SHA384,
    SHA384Salted,
    SHA512,
    SHA512Salted
  }

  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param type an <code>EncryptionType</code> value
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  public String encrypt(EncryptionType type, String text) {
    String hash = "";
    Encrypt e;
    switch (type) {
      case MD5:
        e = new MD5Encryptor();
        hash = e.encrypt(text);
        break;

      case MD5Salted:
        e = new MD5Salted();
        hash = e.encrypt(text);
        break;

      case SHA:
        e = new SHA();
        hash = e.encrypt(text);
        break;

      case SHASalted:
        e = new SHASalted();
        hash = e.encrypt(text);
        break;

      case SHA224:
        e = new SHA224();
        hash = e.encrypt(text);
        break;

      case SHA224Salted:
        e = new SHA224Salted();
        hash = e.encrypt(text);
        break;

      case SHA256:
        e = new SHA256();
        hash = e.encrypt(text);
        break;

      case SHA256Salted:
        e = new SHA256Salted();
        hash = e.encrypt(text);
        break;

      case SHA384:
        e = new SHA384();
        hash = e.encrypt(text);
        break;

      case SHA384Salted:
        e = new SHA384Salted();
        hash = e.encrypt(text);
        break;

      case SHA512:
        e = new SHA512();
        hash = e.encrypt(text);
        break;

      case SHA512Salted:
        e = new SHA512Salted();
        hash = e.encrypt(text);
        break;

      default:
        break;
    }
    return hash;
  }
}
