package networking;

import java.net.UnknownHostException;
import java.util.Scanner;

public class WhoisUI {

  private Whois server;
  private String searchFor;
  private String searchAt;
  private boolean exactMatch;
  private String whois;
  private String searchIn;

  public WhoisUI(Whois server) {
    this.server = server;
  }

  public void getUserInput(String... args) {
    Scanner scanner = new Scanner(System.in);
    getWhoisInput(scanner);
  }

  private void getWhoisInput(Scanner scanner) {
    while (whois == null || whois.isEmpty()) {
      System.out.println("Enter entity name for whois record: ");
      if (scanner.hasNextLine())
        whois = scanner.nextLine().trim();
    }
  }

  public static void main(String... args) {
    try {
      Whois server = new Whois();
      WhoisUI ui = new WhoisUI(server);
      ui.getUserInput(args);
    } catch (UnknownHostException uhe) {
      System.err.println(uhe.getMessage());
    }
  }
}
