package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class IPFinder {

  private static final String UTF_8 = 
    StandardCharsets.UTF_8.name();

  private IPFinder() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try (
    Scanner input = new Scanner(
        System.in, UTF_8);) {
    InetAddress address;
    System.out.print("\n\nEnter host name: ");
    String host = input.next();
      address = InetAddress.getByName(host);
      System.out.println("IP address: "
                         + address);
    } catch (UnknownHostException uhEx) {
      System.err.println("Could not find " + uhEx.getMessage());
    }
  }
}
