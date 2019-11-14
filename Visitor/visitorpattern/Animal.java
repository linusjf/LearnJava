package visitorpattern;

public class Animal implements Transportable {
  private final double fare;
  private final int numberOfAnimals;

  public Animal(double f, int n) {
    fare = f;
    numberOfAnimals = n;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  // Getters //Setters
  public double getFare() {
    return fare;
  }

  public int getNumberOfAnimals() {
    return numberOfAnimals;
  }
}
