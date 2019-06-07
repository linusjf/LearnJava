package player;

import player.FootballPlayer;
import player.Player;
import player.SnookerPlayer;
import player.TennisPlayer;

public enum Type {
  INIT {
    @Override
    public Player createPlayer() {
      throw new AssertionError("Cannot create player...");
    }
  },
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
