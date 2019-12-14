package command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import player.FootballPlayer;
import player.Player;
import player.SnookerPlayer;
import player.TennisPlayer;

public class CreatePlayerCommand {
  private static final Map<String, Command> PLAYERS;

  static {
    final Map<String, Command> players = new HashMap<>();
    players.put("TENNIS", () -> { return new TennisPlayer(); });

    players.put("FOOTBALL", () -> { return new FootballPlayer(); });

    players.put("SNOOKER", () -> { return new SnookerPlayer(); });

    PLAYERS = Collections.unmodifiableMap(players);
  }

  public Player createPlayer(String playerType) {
    Command cmd =
        Objects.requireNonNull(PLAYERS.get(playerType), "Invalid player type: " + playerType);
    return cmd.create();
  }
}
