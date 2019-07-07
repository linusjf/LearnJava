package networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public final class SecureOrderTaker {
  public static final int PORT = 7000;
  public static final String ALGORITHM = "SSL";

  private SecureOrderTaker() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      // The reference implementation only supports X.509 keys
      KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
      // Oracle's default kind of key store
      KeyStore ks = KeyStore.getInstance("JKS");
      // For security, every key store is encrypted with a
      // passphrase that must be provided before we can load
      // it from disk. The passphrase is stored as a char[] array
      // so it can be wiped from memory quickly rather than
      // waiting for a garbage collector.
      char[] password = "2andnotafnord".toCharArray();
      ks.load(Files.newInputStream(Paths.get("jnp4e.keys")), password);
      kmf.init(ks, password);
      SSLContext context = SSLContext.getInstance(ALGORITHM);
      context.init(kmf.getKeyManagers(), null, null);
      Arrays.fill(password, '0');
      SSLServerSocketFactory factory = context.getServerSocketFactory();
      SSLServerSocket server =
          (SSLServerSocket)factory.createServerSocket(PORT);
      // add anonymous (non-authenticated) cipher suites
      String[] supported = server.getSupportedCipherSuites();
      String[] anonCipherSuitesSupported = new String[supported.length];
      int numAnonCipherSuitesSupported = 0;
      for (String instance : supported) {
        if (instance.indexOf("_anon_") > 0) {
          anonCipherSuitesSupported[numAnonCipherSuitesSupported++] = instance;
        }
      }
      String[] oldEnabled = server.getEnabledCipherSuites();
      String[] newEnabled =
          new String[oldEnabled.length + numAnonCipherSuitesSupported];
      System.arraycopy(oldEnabled, 0, newEnabled, 0, oldEnabled.length);
      System.arraycopy(anonCipherSuitesSupported,
                       0,
                       newEnabled,
                       oldEnabled.length,
                       numAnonCipherSuitesSupported);
      server.setEnabledCipherSuites(newEnabled);
      System.out.println("Ready to accept connections...");
      // Now all the set up is complete and we can focus
      // on the actual communication.
      while (true) {
        // This socket will be secure,
        // but there's no indication of that in the code!
        try (Socket theConnection = server.accept()) {
          InputStream in = theConnection.getInputStream();
          int c;
          while ((c = in.read()) != -1) {
            System.out.write(c);
          }
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      }
    } catch (IOException | KeyManagementException | KeyStoreException
             | NoSuchAlgorithmException | CertificateException
             | UnrecoverableKeyException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
