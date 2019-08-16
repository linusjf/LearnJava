package cryptic;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

public enum MacSample {
  ;

  public static void main(String[] args) {
    try {
      // Creating a KeyGenerator object
      KeyGenerator keyGen = KeyGenerator.getInstance("DES");

      // Creating a SecureRandom object
      SecureRandom secRandom = new SecureRandom();

      // Initializing the KeyGenerator
      keyGen.init(secRandom);

      // Creating/Generating a key
      Key key = keyGen.generateKey();

      // Creating a Mac object
      Mac mac = Mac.getInstance("HmacSHA256");

      // Initializing the Mac object
      mac.init(key);

      // Computing the Mac
      String msg = "Hi, how are you?";
      byte[] bytes = msg.getBytes();
      byte[] macResult = mac.doFinal(bytes);

      System.out.println("Mac result:");
      System.out.println(new String(macResult));
      System.out.println("Base64: ");
      System.out.println(Base64.getEncoder().encode(macResult));
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      System.err.println(e);
    }
  }
}
