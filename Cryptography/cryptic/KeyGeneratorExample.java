package cryptic;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public enum KeyGeneratorExample {
  ;

  public static void main(String... args) {
    try {
      // Creating a SecureRandom object
      SecureRandom secRandom = new SecureRandom();

      // Creating a KeyGenerator object
      KeyGenerator keyGen = KeyGenerator.getInstance("DES");

      // Initializing the KeyGenerator
      keyGen.init(secRandom);

      // Creating/Generating a key
      Key key = keyGen.generateKey();

      System.out.println(key);

      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, key);

      String msg = "Hi, how are you?";
      byte[] bytes = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
      System.out.println(Arrays.toString(bytes));
      System.out.println("Base64: ");
      System.out.println(Base64.getEncoder().encodeToString(bytes));
    } catch (BadPaddingException
        | NoSuchPaddingException
        | IllegalBlockSizeException
        | NoSuchAlgorithmException
        | InvalidKeyException e) {
      System.err.println(e);
    }
  }
}
