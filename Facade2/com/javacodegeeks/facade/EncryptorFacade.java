package com.javacodegeeks.facade;
import com.javacodegeeks.abk.Encrypt;
import com.javacodegeeks.abk.MD5Encryptor;
import com.javacodegeeks.abk.MD5Salted;
import com.javacodegeeks.abk.SHA;
import com.javacodegeeks.abk.SHA256;
public class EncryptorFacade {

  public static enum EncryptionType
  {
    MD5,
    MD5Salted,
    SHA,
    SHA256
  };
    public String encrypt(EncryptionType type, String text) {
        String hash = "";
        Encrypt e;
        switch (type)
        {
          case MD5:
            e = new MD5Encryptor();
            hash = e.encrypt(text);
            break;

          case MD5Salted:
            e = new MD5Salted();
            hash = e.encrypt(text);
            break;

          case SHA:
            e = new SHA();
            hash = e.encrypt(text);
            break;
            
          case SHA256:
            e = new SHA256();
            hash = e.encrypt(text);
            break;
        
          default:
            break;
      }
        return hash;
    }
}
