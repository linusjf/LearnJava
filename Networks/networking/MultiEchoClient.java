package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum MultiEchoClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  @SuppressWarnings("PMD.DoNotCallSystemExit")
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
        Scanner networkInput = new Scanner(socket.getInputStream(), UTF_8);
        PrintWriter networkOutput =
            new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8), true);
        Scanner userEntry = new Scanner(System.in, UTF_8); ) {
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
        // Display server's response to user…
        if (networkInput.hasNext()) System.out.println("\nSERVER> " + networkInput.nextLine());
      }
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }
}
