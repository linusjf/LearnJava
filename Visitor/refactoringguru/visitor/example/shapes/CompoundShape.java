package refactoringguru.visitor.example.shapes;

import java.util.ArrayList;
import java.util.List;
import refactoringguru.visitor.example.visitor.Visitor;

public class CompoundShape implements Shape {
  private int id;
  private List<Shape> children = new ArrayList<>();

  public CompoundShape(int id) {
    this.id = id;
  }

  @Override
  public void move(int x, int y) {
    // move shape
  }

  @Override
  public void draw() {
    // draw shape
  }

  public int getId() {
    return id;
  }

  public List<Shape> getChildren() {
    return children;
  }

  @Override
  public String accept(Visitor visitor) {
    return visitor.visitCompoundGraphic(this);
  }

  public void add(Shape shape) {
    children.add(shape);
  }
}
