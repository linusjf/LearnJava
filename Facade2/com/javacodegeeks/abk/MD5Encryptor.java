package com.javacodegeeks.abk;
//CHECKSTYLE:OFF
import static converter.ByteToHex.*;
//CHECKSTYLE:ON
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor implements Encrypt {
  public String encrypt(String text) {
    String hash = "";
    try {
      MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      msgDigest.update(text.getBytes());

      byte textBytes[] = msgDigest.digest();
      hash = getHex4(textBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
