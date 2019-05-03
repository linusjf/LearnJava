package com.javacodegeeks.abk;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5Encryptor implements Encrypt {
    public String encrypt(String text) {
        String hash = "";
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(text.getBytes());

            byte textBytes[] = msgDigest.digest();

            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < textBytes.length; i++) {
                buffer.append(Integer.toString((textBytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            hash = buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
