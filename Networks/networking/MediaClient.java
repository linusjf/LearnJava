package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class MediaClient {
  private static InetAddress host;
  private static final int PORT = 1234;

  private MediaClient() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      host = InetAddress.getLocalHost();
    } catch (UnknownHostException uhEx) {
      System.err.println("Host ID not found!");
      System.exit(1);
    }
    try {
      String message;

      // Set up stream for keyboard entry…
      Scanner userEntry = new Scanner(System.in,
          StandardCharsets.UTF_8.name());
      System.out.print("Enter request (IMAGE/SOUND): ");
      message = userEntry.nextLine();
      while (!"IMAGE".equalsIgnoreCase(message)
             && !"SOUND".equalsIgnoreCase(message)) {
        System.out.println("\nTry again!\n");
        System.out.print("Enter request (IMAGE/SOUND): ");
        message = userEntry.nextLine();
      }

      Socket connection = new Socket(host, PORT);

      // Step 1…
      ObjectInputStream inStream =
          new ObjectInputStream(connection.getInputStream());

      // Step 1 (cont'd)…
      PrintWriter outStream =
          new PrintWriter(new OutputStreamWriter(
                connection.getOutputStream(),
                StandardCharsets.UTF_8.name()), true);

      // Step 2…
      outStream.println(message);
      downloadFile(inStream, message);
      connection.close();
    } catch (IOException | ClassNotFoundException ioEx) {
      System.err.println(ioEx);
    }
  }

  private static void downloadFile(ObjectInputStream inStream, String fileType)
      throws IOException, ClassNotFoundException {
    // Steps 3 and 4…
    // (Note the unusual appearance of the typecast!)
    byte[] byteArray = (byte[])inStream.readObject();
    try (OutputStream mediaStream =
             "IMAGE".equalsIgnoreCase(fileType)
                 ? Files.newOutputStream(Paths.get("image.gif"))
                 : Files.newOutputStream(Paths.get("sound.au"))) {

      // Step 6…
      mediaStream.write(byteArray);
      mediaStream.flush();
      System.out.println("File written successfully");
    }
  }
}
