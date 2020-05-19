package flyweight;

import java.util.HashMap;
import java.util.Map;

// Class used to get a player using HashMap (Returns
// an existing player if a player of given type exists.
// Else creates a new player and returns it.
/**
 * Describe class <code>PlayerFactory</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
final class PlayerFactory { // NOPMD
  /* HashMap stores the reference to the object
  of Terrorist(TS) or CounterTerrorist(CT).  */
  private static Map<String, Player> hm = new HashMap<>();

  private PlayerFactory() {
    throw new IllegalStateException("Private constructor");
  }

  // Method to get a player
  /**
   * Describe <code>getPlayer</code> method here.
   *
   * @param type a <code>String</code> value
   * @return a <code>Player</code> value
   */
  @SuppressWarnings("PMD.SystemPrintln")
  public static Player getPlayer(String type) {
    Player p = hm.get(type);

    /* If an object for TS or CT has already been
    created simply return its reference */
    if (p == null) {
      /* create an object of TS/CT  */
      switch (type) {
        case "Terrorist":
          System.out.println("Terrorist Created");
          p = new Terrorist();
          break;
        case "CounterTerrorist":
          System.out.println("Counter Terrorist Created");
          p = new CounterTerrorist();
          break;
        default:
          System.out.println("Unreachable code!");
          break;
      }

      // Once created insert it into the HashMap
      hm.put(type, p);
    }
    return p;
  }
}
