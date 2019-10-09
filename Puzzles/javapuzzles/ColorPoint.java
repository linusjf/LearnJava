package javapuzzles;

public class ColorPoint extends Point {
  private final String color;

  ColorPoint(int x, int y, String color) {
    super(x, y);
    // 2. Chain to Point constructor
    this.color = color;
  // 5. Initialize blank final-Too late
  }

  @Override
  protected String makeName() {
    // 4. Executes before subclass constructor body!
    return super.makeName() + ":" + color;
  }

  public static void main(String[] args) {
    // 1. Invoke subclass constructor
    System.out.println(new ColorPoint(4, 2, "purple"));
  }
}

@SuppressWarnings("checkstyle:onetoplevelclass")
class Point {
  protected final int x;
  protected final int y;
  private final String name;

  @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
  Point(int x, int y) {
    this.x = x;
    this.y = y;
    name = makeName();
  // 3. Invoke subclass method
  }

  protected String makeName() {
    return "[" + x + "," + y + "]";
  }

  @Override
  public final String toString() {
    return name;
  }
}
