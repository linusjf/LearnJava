package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class FrogsJumpPuzzle {

  int puzzleSize;
  int emptyPadIndex;

  List<Object> slots;

  public FrogsJumpPuzzle(int size) {
    puzzleSize = size;
    slots = new ArrayList<>(2 * size + 2);
    slots.add(Optional.ofNullable(null));
    for (int i = 1; i < size + 1; i++)
      slots.add(new RedFrog());
    slots.add(null);
    for (int i = size + 2; i < 2 * size + 2; i++)
      slots.add(new BlueFrog());
  }

  public void solve() {
    List<Integer> jumpIndices = new ArrayList<>();
    int midIndex = puzzleSize + 1;
    assert slots.indexOf(null) == puzzleSize + 1;
  }

  public static void main(String... args) {
    FrogsJumpPuzzle fjp = new FrogsJumpPuzzle(1);
    fjp.solve();
  }

  interface Frog {}

  static class RedFrog implements Frog {
    // empty class
  }

  static class BlueFrog implements Frog {
    // empty class
  }
}
