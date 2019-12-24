package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class SocketClientDemo {
  private SocketClientDemo() {
    throw new IllegalStateException("Private constructor");
  }

  private static int getPort(String... args) {
    if (args.length > 0) {
      try {
        return Integer.parseInt(args[0]);
      } catch (NumberFormatException nfe) {
        // empty catch block
      }
    }
    return 5432;
  }

  public static void main(String... args) {
    try {
      int port = getPort(args);
      SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket s = (SSLSocket) ssf.createSocket("localhost", port);
      s.setEnabledCipherSuites(new String[] {"TLS_DHE_DSS_WITH_AES_256_CBC_SHA256"});
      s.setEnabledProtocols(new String[] {"TLSv1.2"});

      // SSLParameters sslParams = new SSLParameters();
      // sslParams.setEndpointIdentificationAlgorithm("HTTPS");
      // s.setSSLParameters(sslParams);
      BufferedReader input =
          new BufferedReader(
              new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8.name()));
      System.out.println(input.readLine());
    } catch (IOException ioe) {
      System.err.println("IO exception: " + ioe.getMessage());
    }
  }
}
