package cryptic;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Scanner;

public enum DigitalSignature {
  ;
  public static void main(String... args) {
    try {
      // Accepting text from user
      Scanner sc = new Scanner(System.in, "UTF8");
      System.out.println("Enter some text");
      String msg = sc.nextLine();

      // Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

      // Initializing the key pair generator
      keyPairGen.initialize(2048);

      // Generate the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();

      // Getting the private key from the key pair
      PrivateKey privKey = pair.getPrivate();

      // Creating a Signature object
      Signature sign = Signature.getInstance("SHA256withDSA");

      // Initialize the signature
      sign.initSign(privKey);

      byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);

      // Adding data to the signature
      sign.update(bytes);

      // Calculating the signature
      byte[] signature = sign.sign();

      // Printing the signature
      System.out.println(
        "Digital signature for given text: " + new String(signature, "UTF8")
      );

      System.out.println(Base64.getEncoder().encodeToString(signature));
    } catch (
      UnsupportedEncodingException
      | SignatureException
      | NoSuchAlgorithmException
      | InvalidKeyException ex
    ) {
      System.err.println(ex);
    }
  }
}
