package remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public final class HelloServer {
  private static final String HOST = "localhost";

  private HelloServer() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    try {
      // Create a reference to an
      // implementation object…
      HelloImpl temp = new HelloImpl();

      // Create the string URL holding the
      // object's name…
      String rmiObjectName = "rmi://" + HOST + "/Hello";

      // (Could omit host name here, since 'localhost'
      // would be assumed by default.)
      // 'Bind' the object reference to the name…
      Naming.rebind(rmiObjectName, temp);

      // Display a message so that we know the process
      // has been completed…
      System.out.println("Binding complete…\n");
    } catch (RemoteException | MalformedURLException rme) {
      System.err.println(rme);
    }
  }
}
