package strategy;

import player.FootballPlayer;
import player.Player;
import player.SnookerPlayer;
import player.TennisPlayer;

public enum PlayerTypes {
  TENNIS {

    @Override
    public Player createPlayer() {
      return new TennisPlayer();
    }
  },
  FOOTBALL {

    @Override
    public Player createPlayer() {
      return new FootballPlayer();
    }
  },
  SNOOKER {

    @Override
    public Player createPlayer() {
      return new SnookerPlayer();
    }
  };

  public abstract Player createPlayer();
}
