package strategy;

import player.FootballPlayer;
import player.Player;
import player.SnookerPlayer;
import player.TennisPlayer;

public class PlayerCreator {

  public Player createPlayer(PlayerTypes playerType) {
    switch (playerType) {
      case TENNIS:
        return new TennisPlayer();
      case FOOTBALL:
        return new FootballPlayer();
      case SNOOKER:
        return new SnookerPlayer();
      default:
        throw new IllegalArgumentException(
          "Invalid player type: " + playerType
        );
    }
  }
  
  public Player createPlayer(String playerType) {
    PlayerTypes type = PlayerTypes.valueOf(playerType);
    return createPlayer(type);
  }
}
