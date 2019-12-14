package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum EmailServer {
  ;
  private static ServerSocket serverSocket;
  private static final int PORT = 1234;
  private static final String CLIENT1 = "Dave";
  private static final String CLIENT2 = "Karen";
  private static final int MAX_MESSAGES = 10;
  private static String[] mailbox1 = new String[MAX_MESSAGES];
  private static String[] mailbox2 = new String[MAX_MESSAGES];
  private static int messagesInBox1;
  private static int messagesInBox2;

  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    System.out.println("Opening connection…\n");
    try {
      serverSocket = new ServerSocket(PORT);
    } catch (IOException ioEx) {
      System.out.println("Unable to attach to port!");
      System.exit(1);
    }
    while (true) {
      try {
        runService();
      } catch (InvalidClientException | InvalidRequestException exc) {
        System.out.println("Error: " + exc);
      }
    }
  }

  private static void handleClient1(String sendRead, Scanner input, PrintWriter output) {
    if ("send".equals(sendRead)) {
      doSend(mailbox2, messagesInBox2, input);
      messagesInBox2 = messagesInBox2 < MAX_MESSAGES ? messagesInBox2 + 1 : messagesInBox2;
    } else {
      doRead(mailbox1, messagesInBox1, output);
      messagesInBox1 = 0;
    }
  }

  private static void handleClient2(String sendRead, Scanner input, PrintWriter output) {
    if ("send".equals(sendRead)) {
      doSend(mailbox1, messagesInBox1, input);
      if (messagesInBox1 < MAX_MESSAGES)
        messagesInBox1++;
    } else {
      doRead(mailbox2, messagesInBox2, output);
      messagesInBox2 = 0;
    }
  }

  private static void runService() throws InvalidClientException, InvalidRequestException {
    try {
      Socket link = serverSocket.accept();
      Scanner input = new Scanner(link.getInputStream(), StandardCharsets.UTF_8.name());
      String name = input.nextLine();
      if (!name.equals(CLIENT1) && !name.equals(CLIENT2))
        throw new InvalidClientException();
      String sendRead = input.nextLine();
      if (!"send".equals(sendRead) && !"read".equals(sendRead))
        throw new InvalidRequestException();
      System.out.println("\n" + name + " " + sendRead + "ing mail…");
      PrintWriter output = new PrintWriter(
          new OutputStreamWriter(link.getOutputStream(), StandardCharsets.UTF_8.name()), true);
      if (name.equals(CLIENT1)) {
        handleClient1(sendRead, input, output);
      } else {
        // from client 2
        handleClient2(sendRead, input, output);
      }
      link.close();
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }

  private static void doSend(String[] mailbox, int messagesInBox, Scanner input) {
    /*
       Client has requested 'sending', so server must
       read message from this client and then place
       message into message box for other client (if
       there is room).
    */
    if (messagesInBox == MAX_MESSAGES) {
      System.out.println("\nMessage box full!");
      input.skip("^.*$");
    } else
      mailbox[messagesInBox] = input.nextLine();
  }

  private static void doRead(String[] mailbox, int messagesInBox, PrintWriter output) {
    /*
       Client has requested 'reading', so server must
       read messages from other client's message box and
       then send those messages to the first client.
    */
    System.out.println("\nReading " + messagesInBox + " message(s).\n");
    output.println(messagesInBox);
    for (int i = 0; i < messagesInBox; i++) output.println(mailbox[i]);
  }

  static class InvalidClientException extends Exception {
    private static final long serialVersionUID = 1L;

    InvalidClientException() {
      super("Invalid client name!");
    }

    InvalidClientException(String message) {
      super(message);
    }
  }

  static class InvalidRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    InvalidRequestException() {
      super("Invalid request!");
    }

    InvalidRequestException(String message) {
      super(message);
    }
  }
}
