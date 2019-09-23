package javapuzzles;

@SuppressWarnings("PMD.FinalFieldCouldBeStatic")
public class Puzzle extends Superb {
  final byte x = 1;
  final Byte y = 2;

  @Override
  public String toString() {
    return x + " : " + y;
  }

  public static void main(String... args) {
    new Puzzle();
  }
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD.NonStaticInitializer"})
class Superb {
  // clang-format off
  {
    System.out.println(this);
  }
  // clang-format on
}
