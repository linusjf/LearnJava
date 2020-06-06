package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class IPCharacteristics {
  private IPCharacteristics() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  private static void handleWildcardAddress(InetAddress address) {
    if (address.isAnyLocalAddress()) {
      System.out.println(address + " is a wildcard address.");
    }
  }

  private static void handleLoopbackAddress(InetAddress address) {
    if (address.isLoopbackAddress()) {
      System.out.println(address + " is loopback address.");
    }
  }

  private static void handleLocalGlobalAddress(InetAddress address) {
    if (address.isLinkLocalAddress()) {
      System.out.println(address + " is a link-local address.");
    } else if (address.isSiteLocalAddress()) {
      System.out.println(address + " is a site-local address.");
    } else {
      System.out.println(address + " is a global address.");
    }
  }

  private static void handleCastAddress(InetAddress address) {
    if (address.isMulticastAddress()) {
      if (address.isMCGlobal()) {
        System.out.println(address + " is a global multicast address.");
      } else if (address.isMCOrgLocal()) {
        System.out.println(address
                           + " is an organization wide multicast address.");
      } else if (address.isMCSiteLocal()) {
        System.out.println(address + " is a site wide multicast address.");
      } else if (address.isMCLinkLocal()) {
        System.out.println(address + " is a subnet wide multicast address.");
      } else if (address.isMCNodeLocal()) {
        System.out.println(address
                           + " is an interface-local multicast address.");
      } else {
        System.out.println(address + " is an unknown multicast address type.");
      }
    } else {
      System.out.println(address + " is a unicast address.");
    }
  }

  public static void main(String[] args) {
    for (String arg: args) {
      try {
        InetAddress address = InetAddress.getByName(arg);
        handleWildcardAddress(address);
        handleLoopbackAddress(address);
        handleLocalGlobalAddress(address);
        handleCastAddress(address);
      } catch (UnknownHostException ex) {
        System.err.println("Could not resolve " + arg);
      }
    }
  }
}
