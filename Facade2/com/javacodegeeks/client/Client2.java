package com.javacodegeeks.client;
import com.javacodegeeks.abk.MD5Encryptor;
import com.javacodegeeks.abk.MD5Salted;
import com.javacodegeeks.abk.SHA;
import com.javacodegeeks.abk.SHA256;
import com.javacodegeeks.abk.SHASalted;
import com.javacodegeeks.abk.SHA256Salted;
public class Client2 {
    public static void main(String[] args) {
        String myText = "Encrypt this text";
        MD5Encryptor e= new MD5Encryptor();
        System.out.println("MD5 encryption");
        System.out.println(e.encrypt(myText));
        MD5Salted esalted= new MD5Salted();
        System.out.println("MD5 Salted encryption");
        System.out.println(esalted.encrypt(myText));
        SHA s = new SHA();
        System.out.println("SHA encryption");
        System.out.println(s.encrypt( myText));
        SHASalted ssalted= new SHASalted();
        System.out.println("SHA Salted encryption");
        System.out.println(ssalted.encrypt(myText));
        SHA256 s256 = new SHA256();
        System.out.println("SHA256 encryption");
        System.out.println(s256.encrypt(myText));
        SHA256Salted s256salted= new SHA256Salted();
        System.out.println("SHA256 Salted encryption");
        System.out.println(s256salted.encrypt(myText));
    }
}
