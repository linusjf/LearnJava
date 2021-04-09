package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class FrogsJumpPuzzle {

  int puzzleSize;

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
    assert emptyPadIndex() == puzzleSize + 1;
    int midPoint = emptyPadIndex();
    int startIndex = midPoint - 1;
    int endIndex = midPoint + 1;
    boolean direction = true;
    while (startIndex > 0) {
      if (direction) {
        jumpIndices.add(startIndex);
        int nextIndex = startIndex + 2;
        while (nextIndex <= endIndex) {
          jumpIndices.add(nextIndex);
          nextIndex += 2;
        }
      } else {
        jumpIndices.add(endIndex);
        int nextIndex = endIndex - 2;
        while (nextIndex >= startIndex) {
          jumpIndices.add(nextIndex);
          nextIndex -= 2;
        }
      }
      startIndex--;
      endIndex++;
      direction = !direction;
    }
    startIndex += 2;
    endIndex -= 2;
    while (startIndex != midPoint) {
      if (direction) {
        jumpIndices.add(startIndex);
        int nextIndex = startIndex + 2;
        while (nextIndex <= endIndex) {
          jumpIndices.add(nextIndex);
          nextIndex += 2;
        }
      } else {
        jumpIndices.add(endIndex);
        int nextIndex = endIndex - 2;
        while (nextIndex >= startIndex) {
          jumpIndices.add(nextIndex);
          nextIndex -= 2;
        }
      }
      startIndex++;
      endIndex--;
      direction = !direction;
    }
    jumpIndices.add(midPoint);
    System.out.println(jumpIndices);
  }

  private int emptyPadIndex() {
    return slots.indexOf(null);
  }

  public static void main(String... args) {
    FrogsJumpPuzzle fjp = new FrogsJumpPuzzle(1);
    fjp.solve();
    fjp = new FrogsJumpPuzzle(2);
    fjp.solve();
    fjp = new FrogsJumpPuzzle(3);
    fjp.solve();
    fjp = new FrogsJumpPuzzle(4);
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
