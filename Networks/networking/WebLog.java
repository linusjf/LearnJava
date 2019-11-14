package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class WebLog {

  private WebLog() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    String entry = null;
    try (BufferedReader bin = Files.newBufferedReader(Paths.get(args[0])); ) {
      for (entry = bin.readLine(); entry != null; entry = bin.readLine()) {
        // separate out the IP address
        int index = entry.indexOf(' ');
        String ip = entry.substring(0, index);
        String theRest = entry.substring(index);

        // Ask DNS for the hostname and print it out
        InetAddress address = InetAddress.getByName(ip);
        System.out.println(address.getHostName() + theRest);
      }
    } catch (UnknownHostException e) {
      System.err.println(entry);
    } catch (IOException ex) {
      System.out.println("Exception: " + ex);
    }
  }
}
