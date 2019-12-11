package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum EmailClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;
  private static String name = "";
  private static Scanner userEntry;
  private static int numMessages;

  public static void main(String[] args) {
    try {
      host = InetAddress.getLocalHost();
    } catch (UnknownHostException uhEx) {
      System.out.println("Host ID not found!");
      System.exit(1);
    }
    userEntry = new Scanner(System.in, StandardCharsets.UTF_8.name());
    while (!"Dave".equals(name) && !"Karen".equals(name)) {
      System.out.print("\nEnter name ('Dave' or 'Karen'): ");
      name = userEntry.nextLine();
    }
    talkToServer();
  }

  private static void talkToServer() {
    String option = "y";
    while (!"n".equals(option)) {
      try (Socket link = new Socket(host, PORT);
           Scanner networkInput = new Scanner(link.getInputStream(),
                                              StandardCharsets.UTF_8.name());
           PrintWriter networkOutput = new PrintWriter(
               new OutputStreamWriter(link.getOutputStream(),
                                      StandardCharsets.UTF_8.name()),
               true);) {
        String response = "";
        while (!"read".equals(response) && !"send".equals(response)) {
          System.out.print("\nsend or read? :");
          response = userEntry.nextLine();
        }
        if ("read".equals(response))
          doRead(networkInput, networkOutput);
        else
          doSend(networkOutput);
        System.out.print("\nDo you wish to send or read another (y/n): ");
        option = userEntry.nextLine();
      } catch (IOException ioe) {
        System.err.println(ioe);
      }
    }
  }

  private static void doSend(PrintWriter networkOutput) {
    System.out.println("\nEnter 1-line message: ");
    String message = userEntry.nextLine();
    networkOutput.println(name);
    networkOutput.println("send");
    networkOutput.println(message);
  }

  private static void doRead(Scanner networkInput, PrintWriter networkOutput) {
    networkOutput.println(name);
    networkOutput.println("read");
    if (networkInput.hasNext()) {
      numMessages = networkInput.nextInt();

      // clear line separator
      networkInput.nextLine();
      System.out.println(numMessages + " messages.");
      while (numMessages > 0) {
        System.out.println(networkInput.nextLine());
        numMessages--;
      }
    }
  }
}
