package cryptic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public enum MessageDigestExample {
  ;
  public static void main(String[] args) {
    // Reading data from user
    Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8.name());
    System.out.println("Enter the message");
    String message = sc.nextLine();

    try {
      // Creating the MessageDigest object
      MessageDigest md = MessageDigest.getInstance("SHA-256");

      // Passing data to the created MessageDigest Object
      md.update(message.getBytes(StandardCharsets.UTF_8));

      // Compute the message digest
      byte[] digest = md.digest();

      // Converting the byte array in to HexString format
      StringBuilder hexString = new StringBuilder();

      for (byte token : digest) hexString.append(
        Integer.toHexString(0xFF & token)
      );
      System.out.println("Hex format : " + hexString);
      System.out.println(
        "Base64 format : " + Base64.getEncoder().encodeToString(digest)
      );
    } catch (NoSuchAlgorithmException nsae) {
      System.err.println(nsae);
    }
  }
}
