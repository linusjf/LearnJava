package networking;

import java.io.PrintWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public enum EmailClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;
  private static String name;
  private static Scanner networkInput, userEntry;
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
    try {
    talkToServer();
  } catch (IOException ioe) {
    System.err.println(ioe);
  }
  
  }

  private static void talkToServer() throws IOException {
    String option = "y";
    String message;
    String response;
    do {
      Socket link = new Socket(host,PORT);
      networkInput = new Scanner(link.getInputStream());
networkOutput = new PrintWriter(link.getOutputStream(),true);
      /******************************************************
       CREATE A SOCKET, SET UP INPUT AND OUTPUT STREAMS,
       ACCEPT THE USER'S REQUEST, CALL UP THE APPROPRIATE
       METHOD (doSend OR doRead), CLOSE THE LINK AND THEN
       ASK IF USER WANTS TO DO ANOTHER READ/SEND.
      ******************************************************/
      do {
      System.out.print("\nsend or read? :");
      response = userEntry.nextLine();
      } while (!"read".equals(response) && !"send".equals(response));
      if ("read".equals(response))
        doRead();
      else
        doSend();
      System.out.print("\nDo you wish to send or read another (y/n): ");
      option = userEntry.nextLine();
    } while (!"n".equals(option));
  }

  private static void doSend() {
    System.out.println("\nEnter 1-line message: ");
    String message = userEntry.nextLine();
    networkOutput.println(name);
    networkOutput.println("send");
    networkOutput.println(message);
  }

  private static void doRead()  {
    /*********************************
    BODY OF THIS METHOD REQUIRED
    *********************************/
    networkOutput.println(name);
    networkOutput.println("read");
    int numMessages = networkInput.nextInt();
    // clear line separator
    networkInput.nextLine();
    System.out.println(numMessages + " messages.");
    while (numMessages > 0) {
    System.out.println(networkInput.nextLine());
    }
  }
}
