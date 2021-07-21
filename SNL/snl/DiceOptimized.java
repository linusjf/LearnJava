package snl;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

public class DiceOptimized implements Dice {
  private final PrimitiveIterator.OfInt sequence;

  public DiceOptimized(int numberOfSixes, int last) {
    this.sequence =
        IntStream
            .concat(IntStream.generate(() -> 6).limit(numberOfSixes),
                    IntStream.of(last))
            .iterator();
  }

  public int roll() {
    return sequence.next();
  }
}
