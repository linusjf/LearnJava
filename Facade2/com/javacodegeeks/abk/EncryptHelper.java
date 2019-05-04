package com.javacodegeeks.abk;
import java.security.SecureRandom;
public final class EncryptHelper {
    //Add salt
 public static byte[] getSalt() 
 {
  //Always use a SecureRandom generator
  SecureRandom sr = new SecureRandom();
  //Create array for salt
  // 128 bit salt  more than enough to ensure uniqueness
  byte[] salt = new byte[16];
  //Get a random salt
  sr.nextBytes(salt);
  //return salt
  return salt;
 }
}
