package com.javacodegeeks.abk;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static converter.ByteToHex.*;
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
