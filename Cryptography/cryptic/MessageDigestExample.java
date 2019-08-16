package cryptic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Base64;

public class MessageDigestExample {
  public static void main(String args[]) {
    // Reading data from user
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the message");
    String message = sc.nextLine();

    try {
      // Creating the MessageDigest object
      MessageDigest md = MessageDigest.getInstance("SHA-256");

      // Passing data to the created MessageDigest Object
      md.update(message.getBytes());

      // Compute the message digest
      byte[] digest = md.digest();
      System.out.println(digest);

      // Converting the byte array in to HexString format
      StringBuffer hexString = new StringBuffer();

      for (int i = 0; i < digest.length; i++) {
        hexString.append(Integer.toHexString(0xFF & digest[i]));
      }
      System.out.println("Hex format : " + hexString.toString());
      System.out.println("Base64 format : " + Base64.getEncoder().encodeToString(digest));
    } catch (NoSuchAlgorithmException nsae) {
      System.err.println(nsae);
    }
  }
}
