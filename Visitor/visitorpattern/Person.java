package visitorpattern;

public class Person implements Transportable {
  private final double fare;
  private final int numberOfPersons;

  public Person(double f, int n) {
    fare = f;
    numberOfPersons = n;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  // Getters //Setters
  public int getNumberOfPersons() {
    return numberOfPersons;
  }

  public double getFare() {
    return fare;
  }
}
