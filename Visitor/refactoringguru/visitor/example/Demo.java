package refactoringguru.visitor.example;

import refactoringguru.visitor.example.shapes.Circle;
import refactoringguru.visitor.example.shapes.CompoundShape;
import refactoringguru.visitor.example.shapes.Dot;
import refactoringguru.visitor.example.shapes.Rectangle;
import refactoringguru.visitor.example.shapes.Shape;
import refactoringguru.visitor.example.visitor.XmlExportVisitor;

public class Demo {
  public static void main(String[] args) {
    Dot dot = new Dot(1, 10, 55);
    Circle circle = new Circle(2, 23, 15, 10);
    Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

    CompoundShape compoundShape = new CompoundShape(4);
    compoundShape.add(dot);
    compoundShape.add(circle);
    compoundShape.add(rectangle);

    CompoundShape c = new CompoundShape(5);
    c.add(dot);
    compoundShape.add(c);

    export(circle, compoundShape);
  }

  private static void export(Shape... shapes) {
    XmlExportVisitor exportVisitor = new XmlExportVisitor();
    System.out.println(exportVisitor.export(shapes));
  }
}
