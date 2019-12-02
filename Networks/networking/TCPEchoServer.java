package networking;

// Server that echoes back client's messages.
// At end of dialogue, sends message indicating
// number of messages received. Uses TCP.
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public enum TCPEchoServer {
  ;
  private static ServerSocket serverSocket;
  private static final int PORT = 1234;

  public static void main(String[] args) {
    System.out.printf("Opening port…%n");
    try {
      // Step 1.
      serverSocket = new ServerSocket(PORT);
    } catch (IOException ioEx) {
      System.out.println("Unable to attach to port!");
      System.exit(1);
    }
    while (true)
      handleClient();
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private static void handleClient() {
    try (Socket link = serverSocket.accept();
         PrintWriter output = new PrintWriter(link.getOutputStream(), true);
         Scanner input = new Scanner(link.getInputStream());) {

      int numMessages = 0;
      String message;
      // Step 4.
      while (!"***CLOSE***".equals(message = input.nextLine())) {
        System.out.println("Message received.");
        numMessages++;
        output.println("Message " + numMessages + ": " + message);
      }
      output.println(numMessages + " messages received.");
      // Step 4.
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }
}
