package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public enum PersonnelClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    try {
      host = InetAddress.getLocalHost();
      talkToServer();
    } catch (UnknownHostException uhEx) {
      System.out.println("Host ID not found!");
      System.exit(1);
    } catch (ClassNotFoundException cnfe) {
      System.err.println("Class not found: " + cnfe);
    }
  }

  @SuppressWarnings({"unchecked", "PMD.LawOfDemeter"})
  private static void talkToServer() throws ClassNotFoundException {
    try (Socket socket = new Socket(host, PORT);
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        PrintWriter outStream =
            new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8), true); ) {
      outStream.println("SEND PERSONNEL DETAILS");
      ArrayList<Personnel> response = (ArrayList<Personnel>) inStream.readObject();

      /*
                As in ArrayListSerialise, the compiler will
                issue a warning for the line above.
                Simply ignore this warning.
      */
      int[] staffCount = {0};
      response.stream()
          .forEach(
              person -> {
                System.out.println("\nStaff member " + ++staffCount[0]);
                System.out.println("Payroll number: " + person.getPayNum());
                System.out.println("Surname: " + person.getSurname());
                System.out.println("First names: " + person.getFirstNames());
              });

      System.out.println("\n\n");
    } catch (IOException ioEx) {
      System.err.println(ioEx);
    }
  }
}
