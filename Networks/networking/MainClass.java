package networking;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocketFactory;

public final class MainClass {

  private MainClass() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    try {
      SSLServerSocketFactory ssf =
          (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
      ServerSocket ss = ssf.createServerSocket(5432);
      while (true) {
        Socket s = ss.accept();
        PrintStream out = new PrintStream(s.getOutputStream());
        out.println("Hi");
        out.close();
        s.close();
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
