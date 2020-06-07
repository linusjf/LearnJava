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

  @Override
  public String toString() {
    return "Animal(fare=" + this.getFare() + ", numberOfAnimals=" + this.getNumberOfAnimals() + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Animal)) return false;
    Animal other = (Animal) o;
    if (!other.canEqual((Object) this)) return false;
    if (Double.compare(this.getFare(), other.getFare()) != 0) return false;
    if (this.getNumberOfAnimals() != other.getNumberOfAnimals()) return false;
    return true;
  }

  protected boolean canEqual(Object other) {
    return other instanceof Animal;
  }

  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $fare = Double.doubleToLongBits(this.getFare());
    result = result * PRIME + (int) ($fare >>> 32 ^ $fare);
    result = result * PRIME + this.getNumberOfAnimals();
    return result;
  }
}
