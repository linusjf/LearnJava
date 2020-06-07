package refactoringguru.visitor.example.shapes;

import refactoringguru.visitor.example.visitor.Visitor;

public class Circle extends Dot {
  private final int radius;

  public Circle(int id, int x, int y, int radius) {
    super(id, x, y);
    this.radius = radius;
  }

  @Override
  public String accept(Visitor visitor) {
    return visitor.visitCircle(this);
  }

  public int getRadius() {
    return radius;
  }

  @Override
  public String toString() {
    return "Circle(radius=" + this.getRadius() + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Circle)) return false;
    Circle other = (Circle) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    if (this.getRadius() != other.getRadius()) return false;
    return true;
  }

  protected boolean canEqual(Object other) {
    return other instanceof Circle;
  }

  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = super.hashCode();
    result = result * PRIME + this.getRadius();
    return result;
  }
}
