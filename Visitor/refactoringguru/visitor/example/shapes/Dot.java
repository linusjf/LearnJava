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
}
