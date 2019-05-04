package com.javacodegeeks.abk;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static com.javacodegeeks.abk.EncryptHelper.*;
import static converter.ByteToHex.*;
public class SHA224Salted implements Encrypt {
    public String encrypt(String text) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-224");
 						byte[] salt = getSalt();
           digest.update(salt);
            byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            hash = getHex4(textBytes);
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
