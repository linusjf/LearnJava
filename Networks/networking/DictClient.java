package networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;

public final class DictClient {
  public static final String SERVER = "dict.org";
  public static final int PORT = 2628;
  public static final int TIMEOUT = 15_000;

  private DictClient() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try (Socket socket = new Socket(SERVER, PORT);
        OutputStream out = socket.getOutputStream();
        Writer writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8")); ) {
      for (String word : args) {
        define(word, writer, reader);
      }
      writer.write("quit\r\n");
      writer.flush();
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  static void define(String word, Writer writer, BufferedReader reader)
      throws IOException, UnsupportedEncodingException {
    writer.write("DEFINE fd-eng-lat " + word + "\r\n");
    writer.flush();
    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
      if (line.startsWith("250 ")) { // OK
        return;
      }
      if (line.startsWith("552 ")) { // no match
        System.out.println("No definition found for " + word);
        return;
      }
      if (line.matches("\\d\\d\\d .*")) continue;
      if (line.trim().equals(".")) continue;
      System.out.println(line);
    }
  }
}
