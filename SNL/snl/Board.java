package snl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
  private final int[] snakes;
  private final int[] ladders;

  public Board(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
    this.snakes = flatten(snakes);
    this.ladders = flatten(ladders);
  }
 
  // Internal helper methods.

  private static int[] flatten(Map<Integer, Integer> map) {
    int[] result = new int[101];
    map.forEach((from, to) -> result[from] = to);
    return result;
  }
  
  private static Map<Integer, Integer> expand(int[] matrix) {
    Map<Integer, Integer> result = new HashMap<>();
    for (int from = 1; from < matrix.length; from++) {
      int to = matrix[from];
      if (to != 0)
        result.put(from, to);
    }
    return Map.copyOf(result);
  }
 
  private static Map<Integer, Integer> reverse(Map<Integer, Integer> map) {
    return map.entrySet().stream().collect(
        Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
  }

  public Board reverse() {
    return new Board(reverse(expand(ladders)), reverse(expand(snakes)));
  }

  public void play(GameObserver observer) {
    play(observer, new DiceFair());
  }

  public void play(GameObserver observer, Dice dice) {
    // we start on position 1
    int position = 1; 
    do {
      observer.turn();
      int roll;
      do {
        roll = dice.roll();
        observer.roll(roll);
        position = next(observer, position, roll);
      // 6 rolls again
      } while (roll == 6 && position != 100); 
    } while (position != 100);
    observer.finished();
  }

  private int next(GameObserver observer, int pos, int count) {
    int next = pos + count;
    if (next > 100)
    // bounce off end
      next = 100 - (next % 100);  
    int jump;
    if ((jump = snakes[next]) != 0 || (jump = ladders[next]) != 0) {
      observer.jump(next, jump);
      next = jump;
    }
    return next;
  }
}
