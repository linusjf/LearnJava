package cryptic;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public enum CipherDecrypt {
  ;
  public static void main(String... args) {
    try {

      // Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

      // Initializing the key pair generator
      keyPairGen.initialize(2048);

      // Generate the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();

      // Getting the public key from the key pair
      PublicKey publicKey = pair.getPublic();

      // Creating a Cipher object
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

      // Initializing a Cipher object
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);

      // Add data to the cipher
      byte[] input = "Welcome to Decrypt Example".getBytes();
      cipher.update(input);

      // encrypting the data
      byte[] cipherText = cipher.doFinal();
      System.out.println(new String(cipherText, "UTF8"));

      // Initializing the same cipher for decryption
      cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

      // Decrypting the text
      byte[] decipheredText = cipher.doFinal(cipherText);
      System.out.println(new String(decipheredText));
    } catch (BadPaddingException | NoSuchPaddingException
             | NoSuchAlgorithmException | UnsupportedEncodingException
             | IllegalBlockSizeException | InvalidKeyException e) {
      System.err.println(e);
    }
  }
}
