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

/**
 * Describe class <code>Client2</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum Client2 {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final String myText = "Encrypt this text";
    System.out.println("Client2: " + myText);
    final MD5Encryptor e = new MD5Encryptor();
    System.out.println("MD5 encryption");
    System.out.println(e.encrypt(myText));
    final MD5Salted esalted = new MD5Salted();
    System.out.println("MD5 Salted encryption");
    System.out.println(esalted.encrypt(myText));
    final SHA s = new SHA();
    System.out.println("SHA encryption");
    System.out.println(s.encrypt(myText));
    final SHASalted ssalted = new SHASalted();
    System.out.println("SHA Salted encryption");
    System.out.println(ssalted.encrypt(myText));
    final SHA224 s224 = new SHA224();
    System.out.println("SHA-224 encryption");
    System.out.println(s224.encrypt(myText));
    final SHA224Salted s224salted = new SHA224Salted();
    System.out.println("SHA-224 Salted encryption");
    System.out.println(s224salted.encrypt(myText));
    final SHA256 s256 = new SHA256();
    System.out.println("SHA256 encryption");
    System.out.println(s256.encrypt(myText));
    final SHA256Salted s256salted = new SHA256Salted();
    System.out.println("SHA256 Salted encryption");
    System.out.println(s256salted.encrypt(myText));
    final SHA384 s384 = new SHA384();
    System.out.println("SHA384 encryption");
    System.out.println(s384.encrypt(myText));
    final SHA384Salted s384salted = new SHA384Salted();
    System.out.println("SHA384 Salted encryption");
    System.out.println(s384salted.encrypt(myText));
    final SHA512 s512 = new SHA512();
    System.out.println("SHA512 encryption");
    System.out.println(s512.encrypt(myText));
    final SHA512Salted s512salted = new SHA512Salted();
    System.out.println("SHA512 Salted encryption");
    System.out.println(s512salted.encrypt(myText));
  }
}
