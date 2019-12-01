package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public enum MultiEchoClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;

  public static void main(String[] args) {
    try {
      host = InetAddress.getLocalHost();
    } catch (UnknownHostException uhEx) {
      System.out.println("\nHost ID not found!\n");
      System.exit(1);
    }
    sendMessages();
  }

  private static void sendMessages() {
    try (Socket socket = new Socket(host, PORT);
         Scanner networkInput = new Scanner(socket.getInputStream());
         PrintWriter networkOutput =
             new PrintWriter(socket.getOutputStream(), true);
         Scanner userEntry = new Scanner(System.in);) {
      String message = "";
      while (!"QUIT".equals(message)) {
        System.out.print("Enter message ('QUIT' to exit): ");
        if (userEntry.hasNext()) {
          message = userEntry.nextLine();

          // Send message to server on the
          // socket's output stream…
          // Accept response from server on the
          // socket's intput stream…
          networkOutput.println(message);
        }
        if (networkInput.hasNext())
          // Display server's response to user…
          System.out.println("\nSERVER> " + networkInput.nextLine());
      }
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }
}
