package javapuzzles;

public class Puzzle extends Superb {
  final byte x = 1;
  final Byte y = 2;

  public String toString() {
    return x + " : " + y;
  }

  public static void main(String... args) {
    new Puzzle();
  }
}

class Superb {
  // clang-format off
  {
    System.out.println(this);
  }
  // clang-format on
}
