package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class LookupTask implements Callable<String> {
  private String line;

  public LookupTask(String line) {
    this.line = line;
  }

  @Override
  public String call() {
    try {
      // separate out the IP address
      int index = line.indexOf(' ');
      String address = line.substring(0, index);
      String theRest = line.substring(index);
      String hostname = InetAddress.getByName(address).getHostName();
      return hostname + " " + theRest;
    } catch (UnknownHostException ex) {
      return line;
    }
  }
}
