package networking;

import java.net.UnknownHostException;

public class WhoisUI {

  private Whois server;

  public WhoisUI(Whois server) {
    this.server = server;
  }

  public static void main(String... args) {
try {
    Whois server = new Whois();
 WhoisUI ui = new WhoisUI(server);
} catch (UnknownHostException uhe) {
  System.err.println(uhe.getMessage());
}
  }
}
