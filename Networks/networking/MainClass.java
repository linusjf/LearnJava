package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public final class MainClass {

  private MainClass() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    try {
      SSLServerSocketFactory ssf =
          (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
      SSLServerSocket ss = (SSLServerSocket)ssf.createServerSocket(5432);
      ss.setNeedClientAuth(true);
      ss.setEnabledCipherSuites(
          new String[] {"TLS_DHE_DSS_WITH_AES_256_CBC_SHA256"});
      ss.setEnabledProtocols(new String[] {"TLSv1.2"});
      while (true) {
        Socket socket = ss.accept();
        PrintWriter out = 
          new PrintWriter(new OutputStreamWriter(
                socket.getOutputStream(),
                StandardCharsets.UTF_8.name()), true);
        out.println("Hello World!");
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
