package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This program no longer works as-is. It does not produce the desired results. Using the LOOKUP url
 * and the reversed ip lists produces different results.
 */
public final class SpamCheck {
  public static final String SPAM_LISTER = "sbl.spamhaus.org";
  public static final String POLICY_LISTER = "pbl.spamhaus.org";
  public static final String EXPLOIT_LISTER = "xbl.spamhaus.org";
  public static final String LOOKUP = "https://www.spamhaus.org/query/ip/";

  private SpamCheck() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    for (String arg : args) {
      try {
        if (isSpammer(arg)) {
          System.out.println(arg + " is a known spammer.");
        } else {
          System.out.println(arg + " appears legitimate.");
        }
      } catch (UnknownHostException uhe) {
        System.err.println(uhe.getMessage());
      }
    }
  }

  private static boolean isValidAddress(String reversedIp, String listAddress) {
    try {
      InetAddress.getByName(reversedIp + listAddress);
      return true;
    } catch (UnknownHostException ue) {
      System.err.println(ue.getMessage());
      return false;
    }
  }

  private static boolean isSpammer(String arg) throws UnknownHostException {
    InetAddress address = InetAddress.getByName(arg);
    byte[] quad = address.getAddress();
    String query = "";
    for (byte octet : quad) {
      int unsignedByte = octet < 0 ? octet + 256 : octet;
      query = String.valueOf(unsignedByte).concat(".").concat(query);
    }
    return isValidAddress(query, SPAM_LISTER)
        || isValidAddress(query, POLICY_LISTER)
        || isValidAddress(query, EXPLOIT_LISTER);
  }
}
