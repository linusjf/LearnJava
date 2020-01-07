package cryptic;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public enum CipherSample {
  ;

  private static final Logger LOGGER =
      Logger.getLogger(CipherSample.class.getName());

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public static void main(String... args) {
    try {
      // Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

      // Initializing the key pair generator
      keyPairGen.initialize(2048);

      // Generating the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();

      // Creating a Cipher object
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

      // Initializing a Cipher object
      cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());

      // Adding data to the cipher
      byte[] input =
          "Test data to be encrypted".getBytes(StandardCharsets.UTF_8);
      cipher.update(input);

      // encrypting the data
      byte[] cipherText = cipher.doFinal();
      System.out.println(new String(cipherText, StandardCharsets.UTF_8));
      System.out.println(Base64.getEncoder().encodeToString(cipherText));
    } catch (BadPaddingException | NoSuchPaddingException
             | NoSuchAlgorithmException | IllegalBlockSizeException
             | InvalidKeyException e) {
      LOGGER.severe(e.getMessage());
    }
  }
}
