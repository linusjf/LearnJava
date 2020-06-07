package refactoringguru.visitor.example.shapes;

import refactoringguru.visitor.example.visitor.Visitor;

public class Rectangle implements Shape {
  private final int id;
  private final int x;
  private final int y;
  private final int width;
  private final int height;

  public Rectangle(int id, int x, int y, int width, int height) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public String accept(Visitor visitor) {
    return visitor.visitRectangle(this);
  }

  @Override
  public void move(int xcoord, int ycoord) {
    // move shape
  }

  @Override
  public void draw() {
    // draw shape
  }

  public int getId() {
    return id;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public String toString() {
    return "Rectangle(id=" + this.getId() + ", x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Rectangle)) return false;
    Rectangle other = (Rectangle) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    if (this.getX() != other.getX()) return false;
    if (this.getY() != other.getY()) return false;
    if (this.getWidth() != other.getWidth()) return false;
    if (this.getHeight() != other.getHeight()) return false;
    return true;
  }

  protected boolean canEqual(Object other) {
    return other instanceof Rectangle;
  }

  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    result = result * PRIME + this.getX();
    result = result * PRIME + this.getY();
    result = result * PRIME + this.getWidth();
    result = result * PRIME + this.getHeight();
    return result;
  }
}
