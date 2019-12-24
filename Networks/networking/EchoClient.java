package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public final class EchoClient {
  private static final int ECHO_PORT = 7;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private EchoClient() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({
    "PMD.AvoidLiteralsInIfCondition",
    "PMD.DataflowAnomalyAnalysis",
    "PMD.DoNotCallSystemExit",
    "PMD.LawOfDemeter"
  })
  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.err.println("Usage: java EchoClient <host name> <port number>");
      System.exit(1);
    }
    String hostName = args[0];
    int portNumber;
    try {
      portNumber = Integer.parseInt(args[1]);
      System.out.printf("Connecting to port %d%n", portNumber);
    } catch (NumberFormatException nfe) {
      portNumber = ECHO_PORT;
      System.err.printf("Error parsing input. Connecting to port %d%n", portNumber);
    }
    try (Socket echoSocket = new Socket(hostName, portNumber);
        PrintWriter out =
            new PrintWriter(new OutputStreamWriter(echoSocket.getOutputStream(), UTF_8), true);
        BufferedReader in =
            new BufferedReader(new InputStreamReader(echoSocket.getInputStream(), UTF_8));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, UTF_8))) {
      String userInput;
      while ((userInput = stdIn.readLine()) != null) {
        out.println(userInput);
        System.out.println("echo: " + in.readLine());
      }
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
    } catch (IOException e) {
      System.err.println(
          "Couldn't get I/O for the connection to " + hostName + ": " + e.getMessage());
    }
  }
}
