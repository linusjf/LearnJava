package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public enum ResourceServer {
  ;
  private static ServerSocket serverSocket;
  private static final int PORT = 1234;

  public static void main(String[] args) {
    try {
      serverSocket = new ServerSocket(PORT);
    } catch (IOException e) {
      System.out.println("\nUnable to set up port!");
      System.exit(1);
    }

    // Create a Resource object with
    // a starting resource level of 1…
    Resource item = new Resource(1);

    // Create a Producer thread, passing a reference
    // to the Resource object as an argument to the
    // thread constructor…
    Producer producer = new Producer(item);

    // Start the Producer thread running…
    producer.start();
    while (true) {
      try {
        // Wait for a client to make connection…
        Socket client = serverSocket.accept();
        System.out.println("\nNew client accepted.\n");

        // Create a ClientThread thread to handle all
        // subsequent dialogue with the client, passing
        // references to both the client's socket and
        // the Resource object…
        ClientThread handler = new ClientThread(client, item);

        // Start the ClientThread thread running…
        handler.start();
      } catch (IOException ioe) {
        System.err.println(ioe);
      }
    } // Server will run indefinitely.
  }
}
