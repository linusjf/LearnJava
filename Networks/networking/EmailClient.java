package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public enum EmailClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;
  private static String name;
  private static Scanner networkInput;
  private static Scanner userEntry;
  private static PrintWriter networkOutput;

  public static void main(String[] args) {
    try {
      host = InetAddress.getLocalHost();
    } catch (UnknownHostException uhEx) {
      System.out.println("Host ID not found!");
      System.exit(1);
    }
    userEntry = new Scanner(System.in);
    do {
      System.out.print("\nEnter name ('Dave' or 'Karen'): ");
      name = userEntry.nextLine();
    } while (!"Dave".equals(name) && !"Karen".equals(name));
    talkToServer();
  }

  private static void talkToServer() {
    String option = "y";
    String response;
    do {
      try (Socket link = new Socket(host, PORT)) {
        networkInput = new Scanner(link.getInputStream());
        networkOutput = new PrintWriter(link.getOutputStream(), true);
        do {
          System.out.print("\nsend or read? :");
          response = userEntry.nextLine();
        } while (!"read".equals(response) && !"send".equals(response));
        if ("read".equals(response)) doRead();
        else doSend();
        System.out.print("\nDo you wish to send or read another (y/n): ");
        option = userEntry.nextLine();
        networkInput.close();
        networkOutput.close();
      } catch (IOException ioe) {
        System.err.println(ioe);
      }
    } while (!"n".equals(option));
  }

  private static void doSend() {
    System.out.println("\nEnter 1-line message: ");
    String message = userEntry.nextLine();
    networkOutput.println(name);
    networkOutput.println("send");
    networkOutput.println(message);
  }

  private static void doRead() {
    networkOutput.println(name);
    networkOutput.println("read");
    int numMessages = 0;
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
