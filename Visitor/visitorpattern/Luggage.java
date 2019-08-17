package visitorpattern;

public class Luggage implements Transportable {
  private double fare;
  private double weight;

  public Luggage(double f, double w) {
    fare = f;
    weight = w;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  // Getters //Setters
  public double getWeight() {
    return weight;
  }

  public double getFare() {
    return fare;
  }
}
