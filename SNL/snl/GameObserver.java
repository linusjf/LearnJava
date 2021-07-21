package snl;

public interface GameObserver {
  default void turn() { }
  default void roll(int roll) { }
  default void jump(int from, int to) { }
  default void finished() { }
}
