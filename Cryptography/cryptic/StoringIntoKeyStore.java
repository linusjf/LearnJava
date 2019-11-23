package cryptic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("PMD.HardCodedCryptoKey")
public enum StoringIntoKeyStore {
  ;

  public static void main(String[] args) {
    try {
      // Creating the KeyStore object
      KeyStore keyStore = KeyStore.getInstance("JCEKS");
      String javaHome = System.getProperty("java.home");
      System.out.println("Java Home: " + javaHome);

      // Loading the KeyStore object
      char[] password = "changeit".toCharArray();
      String path = javaHome + "/lib/security/cacerts";
      InputStream is = Files.newInputStream(Paths.get(path));
      keyStore.load(is, password);

      // Creating the KeyStore.ProtectionParameter object
      KeyStore.ProtectionParameter protectionParam =
          new KeyStore.PasswordProtection(password);

      // Creating SecretKey object
      SecretKey mySecretKey = new SecretKeySpec(
          "myPassword".getBytes(StandardCharsets.UTF_8), "DSA");

      // Creating SecretKeyEntry object
      KeyStore.SecretKeyEntry secretKeyEntry =
          new KeyStore.SecretKeyEntry(mySecretKey);
      keyStore.setEntry("secretKeyAlias", secretKeyEntry, protectionParam);

      // Storing the KeyStore object
      OutputStream os = Files.newOutputStream(Paths.get("newKeyStore.jks"));
      keyStore.store(os, password);
      System.out.println("data stored");
    } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException
             | IOException e) {
      System.err.println(e);
    }
  }
}
