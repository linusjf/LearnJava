package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
      Socket connection = new Socket(host, PORT);
      // Step 1…
      ObjectInputStream inStream =
          new ObjectInputStream(connection.getInputStream());
      // Step 1 (cont'd)…
      PrintWriter outStream =
          new PrintWriter(connection.getOutputStream(), true);
      // Set up stream for keyboard entry…
      Scanner userEntry = new Scanner(System.in);
      System.out.print("Enter request (IMAGE/SOUND): ");
      message = userEntry.nextLine();
      while (!"IMAGE".equalsIgnoreCase(message)
             && !"SOUND".equalsIgnoreCase(message)) {
        System.out.println("\nTry again!\n");
        System.out.print("Enter request (IMAGE/SOUND): ");
        message = userEntry.nextLine();
      }
      // Step 2…
      outStream.println(message);
      getFile(inStream, message);
      connection.close();
    } catch (IOException | ClassNotFoundException ioEx) {
      System.err.println(ioEx);
    }
  }

  private static void getFile(ObjectInputStream inStream, String fileType)
      throws IOException, ClassNotFoundException {
    // Steps 3 and 4…
    // (Note the unusual appearance of the typecast!)
    byte[] byteArray = (byte[])inStream.readObject();
    System.out.println(byteArray.length);
    System.out.println(fileType);
    OutputStream mediaStream;
    if ("IMAGE".equalsIgnoreCase(fileType))
      // Step 5…
      mediaStream = Files.newOutputStream(Paths.get("image.gif"));
    else
      // Must be a sound file…
      // Step 5…
      mediaStream = Files.newOutputStream(Paths.get("sound.au"));
    // Step 6…
    mediaStream.write(byteArray);
    mediaStream.flush();
  }
}
