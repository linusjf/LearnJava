package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
      input =
          new Scanner(client.getInputStream(), StandardCharsets.UTF_8.name());
      output =
          new PrintWriter(new OutputStreamWriter(client.getOutputStream(),
                                                 StandardCharsets.UTF_8.name()),
                          true);
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }

  @Override
  public void run() {
    String request = input.nextLine();
    while (!"0".equals(request)) {
      if ("1".equals(request)) {
        item.takeOne();

        // If none available,
        // wait until resource(s)
        // available (and thread is
        // at front of thread queue).
        output.println("Request granted.");
      }
      request = input.nextLine();
    }
    try {
      System.out.println("Closing down connection…");
      client.close();
    } catch (IOException ioEx) {
      System.out.println("Unable to close connection to client!");
    }
  }
}
