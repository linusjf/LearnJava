package remote;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public final class HelloClient {
  private static final String HOST = "localhost";

  private HelloClient() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      if (System.getSecurityManager() == null) {
        System.setSecurityManager(new ZeroSecurityManager());
      }

      // Obtain a reference to the object from the
      // registry and typecast it into the appropriate
      // type…
      Hello greeting = (Hello) Naming.lookup("rmi://" + HOST + "/Hello");

      // Use the above reference to invoke the remote
      // object's method…
      System.out.println("Message received: " + greeting.getGreeting());
    } catch (ConnectException conEx) {
      System.out.println("Unable to connect to server!");
      System.exit(1);
    } catch (NotBoundException nbEx) {
      System.out.println("Unable to bind to naming server!");
      System.exit(1);
    } catch (RemoteException rme) {
      System.err.println(rme);
      System.exit(1);
    } catch (MalformedURLException mue) {
      System.out.println("Malformed url: " + mue);
      System.exit(1);
    }
  }
}
