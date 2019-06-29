package networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public final class LowPortScanner {

  private LowPortScanner() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    String[] hosts = args.length > 0 ? args : new String[] {"localhost"};
    for (String host : hosts) {
      for (int i = 1; i < 1024; i++) {
        try {
          Socket s = new Socket(host, i);
          System.out.println("There is a server on port " + i + " of " + host);
          s.close();
        } catch (UnknownHostException ex) {
          System.err.println(ex);
          break;
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
          // must not be a server on this port
        }
      }
    }
  }
}
