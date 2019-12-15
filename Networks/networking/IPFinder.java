package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class IPFinder {

  private IPFinder() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    String host;
    Scanner input = new Scanner(System.in, StandardCharsets.UTF_8.name());
    InetAddress address;
    System.out.print("\n\nEnter host name: ");
    host = input.next();
    try {
      address = InetAddress.getByName(host);
      System.out.println("IP address: " + address.toString());
    } catch (UnknownHostException uhEx) {
      System.out.println("Could not find " + host);
    }
  }
}
