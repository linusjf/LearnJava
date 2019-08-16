package cryptic;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public enum KeyPairGeneratorExample {
  ;
  public static void main(String... args) {
    try {
      // Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

      // Initializing the KeyPairGenerator
      keyPairGen.initialize(2048);

      // Generating the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();

      // Getting the private key from the key pair
      PrivateKey privKey = pair.getPrivate();
      System.out.printf("Private key : %s%n", privKey);
      // Getting the public key from the key pair
      PublicKey publicKey = pair.getPublic();
      System.out.printf("Public key : %s%n", publicKey);
      System.out.println("Keys generated");
    } catch (NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }
}
