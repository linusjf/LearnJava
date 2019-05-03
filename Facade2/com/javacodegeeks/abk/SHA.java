package com.javacodegeeks.abk;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SHA implements Encrypt {
    public String encrypt(String text) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < textBytes.length; i++) {
             buffer.append(Integer.toString((textBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
                hash = buffer.toString();
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
