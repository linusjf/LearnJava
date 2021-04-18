package cryptic;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Logger;

public enum SignatureVerification {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(SignatureVerification.class.getName());

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public static void main(String... args) {
    try {
      // Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

      // Initializing the key pair generator
      keyPairGen.initialize(2048);

      // Generate the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();

      // Getting the privatekey from the key pair
      PrivateKey privKey = pair.getPrivate();

      // Creating a Signature object
      Signature sign = Signature.getInstance("SHA256withDSA");

      // Initializing the signature
      sign.initSign(privKey);
      byte[] bytes = "Hello, How are you?".getBytes(StandardCharsets.UTF_8);

      // Adding data to the signature
      sign.update(bytes);

      // Calculating the signature
      byte[] signature = sign.sign();

      // Initializing the signature
      sign.initVerify(pair.getPublic());
      sign.update(bytes);

      // Verifying the signature
      boolean bool = sign.verify(signature);

      if (bool)
        System.out.println("Signature verified");
      else
        System.out.println("Signature failed");
    } catch (SignatureException | NoSuchAlgorithmException
             | InvalidKeyException ex) {
      LOGGER.severe(() -> ex.getMessage());
    }
  }
}
