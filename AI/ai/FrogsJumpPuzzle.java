package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class FrogsJumpPuzzle {

  private static final String SEPARATOR =
      "==≠====================================";
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

  public List<Object> frogs() {
    return slots;
  }

  public List<Integer> solve() {
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
    return jumpIndices;
  }

  public void verify(List<Integer> indices) {
    for (int index: indices)
      jump(index);
    assert emptyPadIndex() == puzzleSize + 1;
    for (int i = 1; i < emptyPadIndex(); i++)
      assert slots.get(i) instanceof BlueFrog;
    for (int i = emptyPadIndex() + 1; i < slots.size(); i++)
      assert slots.get(i) instanceof RedFrog;
  }

  private void jump(int index) {
    Frog frog = (Frog)slots.get(index);
    slots.set(emptyPadIndex(), frog);
    slots.set(index, null);
  }

  private int emptyPadIndex() {
    return slots.indexOf(null);
  }

  public static void main(String... args) {
    FrogsJumpPuzzle fjp = new FrogsJumpPuzzle(1);
    System.out.println(fjp.frogs());
    List<Integer> indices = fjp.solve();
    System.out.println(indices);
    fjp.verify(indices);
    System.out.println(fjp.frogs());
    System.out.println(SEPARATOR);
    fjp = new FrogsJumpPuzzle(2);
    System.out.println(fjp.frogs());
    indices = fjp.solve();
    System.out.println(indices);
    fjp.verify(indices);
    System.out.println(fjp.frogs());
    System.out.println(SEPARATOR);
    fjp = new FrogsJumpPuzzle(3);
    System.out.println(fjp.frogs());
    indices = fjp.solve();
    System.out.println(indices);
    fjp.verify(indices);
    System.out.println(fjp.frogs());
    System.out.println(SEPARATOR);
    fjp = new FrogsJumpPuzzle(4);
    System.out.println(fjp.frogs());
    indices = fjp.solve();
    System.out.println(indices);
    fjp.verify(indices);
    System.out.println(fjp.frogs());
    System.out.println(SEPARATOR);
    fjp = new FrogsJumpPuzzle(5);
    System.out.println(fjp.frogs());
    indices = fjp.solve();
    System.out.println(indices);
    fjp.verify(indices);
    System.out.println(fjp.frogs());
    System.out.println(SEPARATOR);
  }

  interface Frog {}

  static class RedFrog implements Frog {
    @Override
    public String toString() {
      return "Red Frog";
    }
  }

  static class BlueFrog implements Frog {
    @Override
    public String toString() {
      return "Blue Frog";
    }
  }
}
