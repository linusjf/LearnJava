package com.javacodegeeks.abk;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.NoSuchProviderException;
import static converter.ByteToHex.*;
public class MD5Salted   implements Encrypt {
    public String encrypt(String text) {
        String hash = "";
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
 						byte[] salt = getSalt();
					
           msgDigest.update(salt);
            byte textBytes[] = msgDigest.digest(text.getBytes());
            hash = getHex4(textBytes);
        } catch (NoSuchAlgorithmException |
NoSuchProviderException e) {
            e.printStackTrace();
        }
        return hash;
    }

    //Add salt
 private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
 {
  //Always use a SecureRandom generator
  SecureRandom sr = new SecureRandom();
  //Create array for salt
  byte[] salt = new byte[16];
  //Get a random salt
  sr.nextBytes(salt);
  //return salt
  return salt;
 }
}
