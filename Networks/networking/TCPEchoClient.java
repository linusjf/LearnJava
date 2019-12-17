package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum TCPEchoClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;

  @SuppressWarnings("PMD.DoNotCallSystemExit")
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
         Scanner input =
             new Scanner(link.getInputStream(),
                         StandardCharsets.UTF_8.name());
         PrintWriter output = new PrintWriter(
             new OutputStreamWriter(
                 link.getOutputStream(),
                 StandardCharsets.UTF_8.name()),
             true);
         Scanner userEntry = new Scanner(
             System.in, StandardCharsets.UTF_8.name());) {
      // Set up stream for keyboard entry…
      String message = "";
      while (!"***CLOSE***".equals(message)) {
        System.out.print(
            "Enter message (***CLOSE*** to end): ");
        if (userEntry.hasNext()) {
          message = userEntry.nextLine();
          output.println(message);
        }
        if (input.hasNext())
          System.out.println("\nSERVER> "
                             + input.nextLine());
      }
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }
}
