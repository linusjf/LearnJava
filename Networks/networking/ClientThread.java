package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ClientThread extends Thread {
  private final Socket client;
  private final Resource item;
  private Scanner input;
  private PrintWriter output;

  ClientThread(Socket socket, Resource resource) {
    super();
    client = socket;
    item = resource;
    try {
      // Create input and output streams
      // on the socket…
      input = new Scanner(client.getInputStream());
      output = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }

  @Override
  public void run() {
    String request = "";
    do {
      request = input.nextLine();
      if ("1".equals(request)) {
        item.takeOne();

        // If none available,
        // wait until resource(s)
        // available (and thread is
        // at front of thread queue).
        output.println("Request granted.");
      }
    } while (!"0".equals(request));
    try {
      System.out.println("Closing down connection…");
      client.close();
    } catch (IOException ioEx) {
      System.out.println("Unable to close connection to client!");
    }
  }
}
