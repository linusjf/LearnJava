package networking;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public final class MediaServer {
  private static ServerSocket serverSocket;
  private static final int PORT = 1234;

  private MediaServer() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    System.out.println("Opening port…\n");
    try {
      serverSocket = new ServerSocket(PORT);
    } catch (IOException ioEx) {
      System.err.println("Unable to attach to port!");
      System.exit(1);
    }
    while (true) {
      try (Socket connection = serverSocket.accept();

           // Step 1…
           Scanner inStream = new Scanner(connection.getInputStream(),
                                          StandardCharsets.UTF_8.name());) {

        // Step 2…
        String message = inStream.nextLine();
        System.out.println(message);
        if ("IMAGE".equals(message))
          sendFile("beesting.jpg",
                   new ObjectOutputStream(connection.getOutputStream()));
        if ("SOUND".equals(message))
          sendFile("cuckoo.wav",
                   new ObjectOutputStream(connection.getOutputStream()));
      } catch (IOException ioEx) {
        System.err.println(ioEx);
      }
    }
  }

  private static void sendFile(String fileName, ObjectOutputStream outStream)
      throws IOException {
    // Step 3…
    try (InputStream fileIn = Files.newInputStream(Paths.get(fileName))) {

      // Step 4…
      long fileLen = new File(fileName).length();

      // Step 5…
      int intFileLen = (int)fileLen;

      // Step 5 (cont'd)…
      byte[] byteArray = new byte[intFileLen];

      // Step 6…
      if (fileIn.read(byteArray) != -1) {
        // Step 7…
        outStream.writeObject(byteArray);
        outStream.flush();
      }
    }
  }
}
