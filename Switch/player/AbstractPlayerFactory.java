package player;

public interface AbstractPlayerFactory {
  Player createPlayer(Type type, int delta);
}
