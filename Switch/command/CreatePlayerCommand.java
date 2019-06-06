package command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import player.FootballPlayer;
import player.Player;
import player.SnookerPlayer;
import player.TennisPlayer;

public class CreatePlayerCommand {
  private static final Map<String, Command> PLAYERS;

  static {
    final Map<String, Command> players = new HashMap<>();
    players.put("TENNIS", new Command() {
      @Override
      public Player create() {
        return new TennisPlayer();
      }
    });

    players.put("FOOTBALL", new Command() {
      @Override
      public Player create() {
        return new FootballPlayer();
      }
    });

    players.put("SNOOKER", new Command() {
      @Override
      public Player create() {
        return new SnookerPlayer();
      }
    });

    PLAYERS = Collections.unmodifiableMap(players);
  }

  public Player createPlayer(String playerType) {
    Command cmd = PLAYERS.get(playerType);

    if (cmd == null) {
      throw new IllegalArgumentException("Invalid player type: " + playerType);
    }
    return cmd.create();
  }
}
