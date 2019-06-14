package networking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class WebLog {

  private WebLog() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    String entry = null;
    try (FileInputStream fin = new FileInputStream(args[0]);
        Reader in = new InputStreamReader(fin);
        BufferedReader bin = new BufferedReader(in); ) {
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
