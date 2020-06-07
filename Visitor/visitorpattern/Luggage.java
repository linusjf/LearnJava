package visitorpattern;

public class Luggage implements Transportable {
  private final double fare;
  private final double weight;

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

  @Override
  public String toString() {
    return "Luggage(fare=" + this.getFare() + ", weight=" + this.getWeight() + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Luggage)) return false;
    Luggage other = (Luggage) o;
    if (!other.canEqual((Object) this)) return false;
    if (Double.compare(this.getFare(), other.getFare()) != 0) return false;
    if (Double.compare(this.getWeight(), other.getWeight()) != 0) return false;
    return true;
  }

  protected boolean canEqual(Object other) {
    return other instanceof Luggage;
  }

  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $fare = Double.doubleToLongBits(this.getFare());
    result = result * PRIME + (int) ($fare >>> 32 ^ $fare);
    long $weight = Double.doubleToLongBits(this.getWeight());
    result = result * PRIME + (int) ($weight >>> 32 ^ $weight);
    return result;
  }
}
