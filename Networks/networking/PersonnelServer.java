package networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public enum PersonnelServer {
  ;
  private static ServerSocket serverSocket;
  private static final int PORT = 1234;
  private static Socket socket;
  private static List<Personnel> staffListOut;
  private static Scanner inStream;
  private static ObjectOutputStream outStream;

  public static void main(String[] args) {
    System.out.println("Opening port…\n");
    try {
      serverSocket = new ServerSocket(PORT);
    } catch (IOException ioEx) {
      System.out.println("Unable to attach to port!");
      System.exit(1);
    }
    staffListOut = new ArrayList<>();

    // clang-format off
    Personnel[] staff = {
      new Personnel(123_456, "Smith", "John"),
      new Personnel(234_567, "Jones", "Sally Ann"),
      new Personnel(999_999, "Black", "James Paul"),
    };

    // clang-format on
    for (Personnel person: staff)
      staffListOut.add(person);
    startServer();
  }

  private static void startServer() {
    while (true) {
      try {
        socket = serverSocket.accept();
        inStream = new Scanner(socket.getInputStream(),
            StandardCharsets.UTF_8.name());
        outStream = new ObjectOutputStream(socket.getOutputStream());

        /*
               The above line and associated declaration
               are the only really new code featured in
               this example.
        */
        String message = inStream.nextLine();
        if ("SEND PERSONNEL DETAILS".equals(message)) {
          outStream.writeObject(staffListOut);
          outStream.close();
        }
        System.out.println("\n* Closing connection… *");
        socket.close();
      } catch (IOException ioEx) {
        System.err.println(ioEx);
      }
    }
  }
}
