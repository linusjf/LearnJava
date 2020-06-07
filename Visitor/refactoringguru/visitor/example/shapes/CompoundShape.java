package refactoringguru.visitor.example.shapes;

import java.util.ArrayList;
import java.util.List;
import refactoringguru.visitor.example.visitor.Visitor;

public class CompoundShape implements Shape {
  private final int id;
  private final List<Shape> children = new ArrayList<>();

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

  @Override
  public String toString() {
    return "CompoundShape(id=" + this.getId()
        + ", children=" + this.getChildren() + ")";
  }

  @SuppressWarnings("all")
  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof CompoundShape))
      return false;
    CompoundShape other = (CompoundShape)o;
    if (!other.canEqual((Object)this))
      return false;
    if (this.getId() != other.getId())
      return false;
    Object this$children = this.getChildren();
    Object other$children = other.getChildren();
    if (this$children == null ? other$children != null
                              : !this$children.equals(other$children))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof CompoundShape;
  }

  @SuppressWarnings("all")
  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    Object $children = this.getChildren();
    result = result * PRIME + ($children == null ? 43 : $children.hashCode());
    return result;
  }
}
