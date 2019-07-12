package refactoringguru.visitor.example.shapes;

import refactoringguru.visitor.example.visitor.Visitor;

public interface Shape {
  void move(int x, int y);

  void draw();

  String accept(Visitor visitor);
}
