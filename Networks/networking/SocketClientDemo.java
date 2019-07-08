package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

public final class SocketClientDemo {
  private SocketClientDemo() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    try {
      SSLSocketFactory ssf = (SSLSocketFactory)SSLSocketFactory.getDefault();
      Socket s = ssf.createSocket("localhost", 5432);
      BufferedReader in =
          new BufferedReader(new InputStreamReader(s.getInputStream()));
      String x = in.readLine();
      System.out.println(x);
      in.close();
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
