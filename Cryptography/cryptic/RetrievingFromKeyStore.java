package cryptic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("PMD.HardCodedCryptoKey")
public enum RetrievingFromKeyStore {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(RetrievingFromKeyStore.class.getName());

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public static void main(String... args) {
    try {
      // Creating the KeyStore object
      KeyStore keyStore = KeyStore.getInstance("JCEKS");

      String javaHome = System.getProperty("java.home");
      System.out.println("Java Home: " + javaHome);

      // Loading the the KeyStore object
      char[] password = "changeit".toCharArray();
      String path = javaHome + "/lib/security/cacerts";
      InputStream is = Files.newInputStream(Paths.get(path));
      keyStore.load(is, password);

      // Creating the KeyStore.ProtectionParameter object
      ProtectionParameter protectionParam =
          new KeyStore.PasswordProtection(password);

      // Creating SecretKey object
      SecretKey mySecretKey = new SecretKeySpec(
          "myPassword".getBytes(StandardCharsets.UTF_8), "DSA");

      // Creating SecretKeyEntry object
      SecretKeyEntry secretKeyEntry = new SecretKeyEntry(mySecretKey);
      keyStore.setEntry("secretKeyAlias", secretKeyEntry, protectionParam);

      // Storing the KeyStore object
      OutputStream os = Files.newOutputStream(Paths.get("newKeyStore2.jks"));
      keyStore.store(os, password);

      // Creating the KeyStore.SecretKeyEntry object
      SecretKeyEntry secretKeyEnt =
          (SecretKeyEntry)keyStore.getEntry("secretKeyAlias", protectionParam);

      // Creating SecretKey object
      SecretKey mysecretKey = secretKeyEnt.getSecretKey();
      System.out.println("Algorithm used to generate key : "
                         + mysecretKey.getAlgorithm());
      System.out.println("Format used for the key: " + mysecretKey.getFormat());
    } catch (UnrecoverableEntryException | CertificateException
             | NoSuchAlgorithmException | KeyStoreException | IOException e) {
      LOGGER.severe(e.getMessage());
    }
  }
}
