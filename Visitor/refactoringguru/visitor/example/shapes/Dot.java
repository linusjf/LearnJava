package refactoringguru.visitor.example.shapes;

import refactoringguru.visitor.example.visitor.Visitor;

@SuppressWarnings("PMD.ShortClassName")
public class Dot implements Shape {
  private int id;
  private int xPoint;
  private int yPoint;

  public Dot() {
    // empty constructor
  }

  public Dot(int id, int x, int y) {
    this.id = id;
    this.xPoint = x;
    this.yPoint = y;
  }

  @Override
  public void move(int x, int y) {
    // move shape
  }

  @Override
  public void draw() {
    // draw shape
  }

  @Override
  public String accept(Visitor visitor) {
    return visitor.visitDot(this);
  }

  public int getX() {
    return xPoint;
  }

  public int getY() {
    return yPoint;
  }

  public int getId() {
    return id;
  }

  @SuppressWarnings("all")
  @Override
  public String toString() {
    return "Dot(id=" + this.getId() + ", xPoint=" + this.xPoint
        + ", yPoint=" + this.yPoint + ")";
  }

  @SuppressWarnings("all")
  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Dot))
      return false;
    Dot other = (Dot)o;
    if (!other.canEqual((Object)this))
      return false;
    if (this.getId() != other.getId())
      return false;
    if (this.xPoint != other.xPoint)
      return false;
    if (this.yPoint != other.yPoint)
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Dot;
  }

  @SuppressWarnings("all")
  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    result = result * PRIME + this.xPoint;
    result = result * PRIME + this.yPoint;
    return result;
  }
}
