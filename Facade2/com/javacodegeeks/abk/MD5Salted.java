package com.javacodegeeks.abk;
//CHECKSTYLE:OFF
import static com.javacodegeeks.abk.EncryptHelper.*;
import static converter.ByteToHex.*;
//CHECKSTYLE:ON

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Salted implements Encrypt {
  public String encrypt(String text) {
    String hash = "";
    try {
      MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      byte[] salt = getSalt();
      msgDigest.update(salt);
      byte textBytes[] = msgDigest.digest(text.getBytes());
      hash = getHex4(textBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
