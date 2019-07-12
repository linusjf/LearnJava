package refactoringguru.visitor.example.visitor;

import refactoringguru.visitor.example.shapes.Circle;
import refactoringguru.visitor.example.shapes.CompoundShape;
import refactoringguru.visitor.example.shapes.Dot;
import refactoringguru.visitor.example.shapes.Rectangle;

public interface Visitor {
  String visitDot(Dot dot);

  String visitCircle(Circle circle);

  String visitRectangle(Rectangle rectangle);

  String visitCompoundGraphic(CompoundShape cg);
}
