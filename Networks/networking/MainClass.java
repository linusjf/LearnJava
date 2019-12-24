package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public final class MainClass {
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private MainClass() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String... args) {
    try {
      SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
      SSLServerSocket ss = createServerSocket(ssf);
      acceptConnections(ss);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static void acceptConnections(SSLServerSocket ss) throws IOException {
    while (true) {
      try (Socket socket = ss.accept();
          PrintWriter out =
              new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8), true); ) {
        out.println("Hello World!");
      }
    }
  }

  private static SSLServerSocket createServerSocket(SSLServerSocketFactory ssf) throws IOException {
    SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(5432);
    setProperties(ss);
    return ss;
  }

  private static void setProperties(SSLServerSocket ss) throws IOException {
    ss.setNeedClientAuth(true);
    ss.setEnabledCipherSuites(new String[] {"TLS_DHE_DSS_WITH_AES_256_CBC_SHA256"});
    ss.setEnabledProtocols(new String[] {"TLSv1.2"});
  }
}
