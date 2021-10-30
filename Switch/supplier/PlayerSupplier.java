package supplier;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import player.FootballPlayer;
import player.Player;
import player.SnookerPlayer;
import player.TennisPlayer;

public class PlayerSupplier {
  private static final Map<String, Supplier<Player>> PLAYER_SUPPLIER;

  static {
    final Map<String, Supplier<Player>> players = new HashMap<>();
    players.put("TENNIS", TennisPlayer::new);
    players.put("FOOTBALL", FootballPlayer::new);
    players.put("SNOOKER", SnookerPlayer::new);

    PLAYER_SUPPLIER = Collections.unmodifiableMap(players);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public Player supplyPlayer(String playerType) {
    Supplier<Player> player = getPlayerSupplier(playerType);
    return player.get();
  }

  public Supplier<Player> getPlayerSupplier(String playerType) {
    return requireNonNull(PLAYER_SUPPLIER.get(playerType),
                          "Invalid player type: " + playerType);
  }
}
