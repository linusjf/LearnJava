package com.javacodegeeks.client;

import static com.javacodegeeks.facade.EncryptorFacade.EncryptionType.*;

import com.javacodegeeks.facade.EncryptorFacade;

/**
 * Describe class <code>Client</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Client {
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    String myText = "Encrypt this text";
    System.out.println("Text to be encrypted: " + myText);
    EncryptorFacade e = new EncryptorFacade();
    System.out.println("MD5 encryption");
    System.out.println(e.encrypt(MD5, myText));
    System.out.println("MD5 salted encryption");
    System.out.println(e.encrypt(MD5Salted, myText));
    System.out.println("SHA encryption");
    System.out.println(e.encrypt(SHA, myText));
    System.out.println("SHA salted encryption");
    System.out.println(e.encrypt(SHASalted, myText));
    System.out.println("SHA-224 encryption");
    System.out.println(e.encrypt(SHA224, myText));
    System.out.println("SHA-224 salted encryption");
    System.out.println(e.encrypt(SHA224Salted, myText));
    System.out.println("SHA256 encryption");
    System.out.println(e.encrypt(SHA256, myText));
    System.out.println("SHA256Salted encryption");
    System.out.println(e.encrypt(SHA256Salted, myText));
    System.out.println("SHA384 encryption");
    System.out.println(e.encrypt(SHA384, myText));
    System.out.println("SHA384Salted encryption");
    System.out.println(e.encrypt(SHA384Salted, myText));
    System.out.println("SHA512 encryption");
    System.out.println(e.encrypt(SHA512, myText));
    System.out.println("SHA512Salted encryption");
    System.out.println(e.encrypt(SHA512Salted, myText));
  }
}
