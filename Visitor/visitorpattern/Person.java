package visitorpattern;

import java.util.Objects;

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

  @SuppressWarnings("all")
  @Override
  public String toString() {
    return "Person(fare=" + this.getFare()
        + ", numberOfPersons=" + this.getNumberOfPersons() + ")";
  }

  @SuppressWarnings("all")
  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Person))
      return false;
    Person other = (Person)o;
    if (!other.canEqual((Object)this))
      return false;
    if (Double.compare(this.getFare(), other.getFare()) != 0)
      return false;
    if (this.getNumberOfPersons() != other.getNumberOfPersons())
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Person;
  }

  @SuppressWarnings("all")
  @Override
  public int hashCode() {
    return Objects.hash(fare, numberOfPersons);
  }
}
