package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public enum TCPEchoClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;

  public static void main(String[] args) {
    try {
      host = InetAddress.getLocalHost();
    } catch (UnknownHostException uhEx) {
      System.out.println("Host ID not found!");
      System.exit(1);
    }
    accessServer();
  }

  private static void accessServer() {
    try (Socket link = new Socket(host, PORT);
        Scanner input = new Scanner(link.getInputStream());
        PrintWriter output = new PrintWriter(link.getOutputStream(), true); ) {
      // Set up stream for keyboard entry…
      Scanner userEntry = new Scanner(System.in);
      String message = "";
      String response;
      do {
        System.out.print("Enter message (***CLOSE*** to end): ");
        if (userEntry.hasNext()) {
          message = userEntry.nextLine();
          output.println(message);
        }
        if (input.hasNext()) {
          response = input.nextLine();
          System.out.println("\nSERVER> " + response);
        }
      } while (!"***CLOSE***".equals(message));
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }
}
