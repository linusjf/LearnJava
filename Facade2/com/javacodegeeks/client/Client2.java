package com.javacodegeeks.client;

import com.javacodegeeks.abk.MD5Encryptor;
import com.javacodegeeks.abk.MD5Salted;
import com.javacodegeeks.abk.SHA;
import com.javacodegeeks.abk.SHA224;
import com.javacodegeeks.abk.SHA224Salted;
import com.javacodegeeks.abk.SHA256;
import com.javacodegeeks.abk.SHA256Salted;
import com.javacodegeeks.abk.SHA384;
import com.javacodegeeks.abk.SHA384Salted;
import com.javacodegeeks.abk.SHA512;
import com.javacodegeeks.abk.SHA512Salted;
import com.javacodegeeks.abk.SHASalted;

public class Client2 {
  public static void main(String[] args) {
    String myText = "Encrypt this text";
    System.out.println("Client2: " + myText);
    MD5Encryptor e = new MD5Encryptor();
    System.out.println("MD5 encryption");
    System.out.println(e.encrypt(myText));
    MD5Salted esalted = new MD5Salted();
    System.out.println("MD5 Salted encryption");
    System.out.println(esalted.encrypt(myText));
    SHA s = new SHA();
    System.out.println("SHA encryption");
    System.out.println(s.encrypt(myText));
    SHASalted ssalted = new SHASalted();
    System.out.println("SHA Salted encryption");
    System.out.println(ssalted.encrypt(myText));
    SHA224 s224 = new SHA224();
    System.out.println("SHA-224 encryption");
    System.out.println(s224.encrypt(myText));
    SHA224Salted s224salted = new SHA224Salted();
    System.out.println("SHA-224 Salted encryption");
    System.out.println(s224salted.encrypt(myText));
    SHA256 s256 = new SHA256();
    System.out.println("SHA256 encryption");
    System.out.println(s256.encrypt(myText));
    SHA256Salted s256salted = new SHA256Salted();
    System.out.println("SHA256 Salted encryption");
    System.out.println(s256salted.encrypt(myText));
    SHA384 s384 = new SHA384();
    System.out.println("SHA384 encryption");
    System.out.println(s384.encrypt(myText));
    SHA384Salted s384salted = new SHA384Salted();
    System.out.println("SHA384 Salted encryption");
    System.out.println(s384salted.encrypt(myText));
    SHA512 s512 = new SHA512();
    System.out.println("SHA512 encryption");
    System.out.println(s512.encrypt(myText));
    SHA512Salted s512salted = new SHA512Salted();
    System.out.println("SHA512 Salted encryption");
    System.out.println(s512salted.encrypt(myText));
  }
}
