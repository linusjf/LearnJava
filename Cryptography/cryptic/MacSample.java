package cryptic;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

public enum MacSample {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
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
      computeMac(key);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      System.err.println(e);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void computeMac(Key key) throws NoSuchAlgorithmException,
          InvalidKeyException {

      // Creating a Mac object
      Mac mac = Mac.getInstance("HmacSHA256");

      // Initializing the Mac object
      mac.init(key);

      // Computing the Mac
      String msg = "Hi, how are you?";
      byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
      byte[] macResult = mac.doFinal(bytes);

      System.out.println("Mac result:");
      System.out.println(new String(macResult, StandardCharsets.UTF_8));
      System.out.println("Base64: ");
      System.out.println(Base64.getEncoder().encodeToString(macResult));
  }
}
