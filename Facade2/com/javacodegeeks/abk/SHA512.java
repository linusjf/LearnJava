package com.javacodegeeks.abk;
//CHECKSTYLE:OFF 
import static converter.ByteToHex.*;
//CHECKSTYLE:ON
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512 implements Encrypt {
  public String encrypt(String text) {
    String hash = "";
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));

      hash = getHex4(textBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
