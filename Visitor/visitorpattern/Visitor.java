package visitorpattern;

public interface Visitor {
  void visit(Person p);

  void visit(Animal a);

  void visit(Luggage l);
}
