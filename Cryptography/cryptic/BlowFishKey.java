package cryptic;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * This program generates a Blowfish key, retrieves its raw bytes, and then reinstantiates a
 * Blowfish key from the key bytes. The reinstantiated key is used to initialize a Blowfish cipher
 * for encryption.
 */
public enum BlowFishKey {
  ;
  public static void main(String[] args) {
    try {
      KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
      SecretKey skey = kgen.generateKey();
      byte[] raw = skey.getEncoded();
      SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");

      Cipher cipher = Cipher.getInstance("Blowfish");
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
      byte[] encrypted = cipher.doFinal(
        "This is just an example".getBytes(StandardCharsets.UTF_8)
      );
      System.out.println(
        "Base 64: " + Base64.getEncoder().encodeToString(encrypted)
      );
    } catch (
      IllegalBlockSizeException
      | InvalidKeyException
      | BadPaddingException
      | NoSuchPaddingException
      | NoSuchAlgorithmException exc
    ) {
      System.err.println(exc);
    }
  }
}
