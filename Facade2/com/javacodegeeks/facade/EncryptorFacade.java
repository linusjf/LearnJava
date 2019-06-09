package com.javacodegeeks.facade;

import com.javacodegeeks.abk.BCryptor;
import com.javacodegeeks.abk.Encrypt;
import com.javacodegeeks.abk.MD5Encryptor;
import com.javacodegeeks.abk.MD5Salted;
import com.javacodegeeks.abk.PBKDFEncryptor;
import com.javacodegeeks.abk.SCryptor;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Describe class <code>EncryptorFacade</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class EncryptorFacade {
  public enum EncryptionType {
    MD5,
    MD5SALTED,
    SHA,
    SHASALTED,
    SHA224,
    SHA224SALTED,
    SHA256,
    SHA256SALTED,
    SHA384,
    SHA384SALTED,
    SHA512,
    SHA512SALTED,
    SCRYPT,
    BCRYPT,
    PBKDF,
  }

  private static final Map<EncryptionType, Supplier<Encrypt>> ENCRYPTOR_SUPPLIER;

  static {
    final Map<EncryptionType, Supplier<Encrypt>> encryptors = new HashMap<>();
    encryptors.put(EncryptionType.MD5, MD5Encryptor::new);
    encryptors.put(EncryptionType.MD5SALTED, MD5Salted::new);
    encryptors.put(EncryptionType.SHA, SHA::new);
    encryptors.put(EncryptionType.SHASALTED, SHASalted::new);
    encryptors.put(EncryptionType.SHA224, SHA224::new);
    encryptors.put(EncryptionType.SHA224SALTED, SHA224Salted::new);
    encryptors.put(EncryptionType.SHA256, SHA256::new);
    encryptors.put(EncryptionType.SHA256SALTED, SHA256Salted::new);
    encryptors.put(EncryptionType.SHA384, SHA384::new);
    encryptors.put(EncryptionType.SHA384SALTED, SHA384Salted::new);
    encryptors.put(EncryptionType.SHA512, SHA512::new);
    encryptors.put(EncryptionType.SHA512SALTED, SHA512Salted::new);
    encryptors.put(EncryptionType.BCRYPT, BCryptor::new);
    encryptors.put(EncryptionType.SCRYPT, SCryptor::new);
    encryptors.put(EncryptionType.PBKDF, PBKDFEncryptor::new);
    ENCRYPTOR_SUPPLIER = Collections.unmodifiableMap(encryptors);
  }

  public Encrypt supplyEncryptor(EncryptionType type) {
    Supplier<Encrypt> supplier = ENCRYPTOR_SUPPLIER.get(type);
    if (supplier == null) throw new IllegalArgumentException("No encryptor exists for " + type);
    return supplier.get();
  }

  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param type an <code>EncryptionType</code> value
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  public String encrypt(EncryptionType type, String text) {
    final Encrypt e = supplyEncryptor(type);
    return e.encrypt(text);
  }
}
