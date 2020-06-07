package visitorpattern;

@SuppressWarnings("PMD.ShortClassName")
public class Taxi implements Visitor {
  private double totalFare;

  @Override
  public void visit(Person p) {
    totalFare = totalFare + (p.getFare() * p.getNumberOfPersons());
  }

  @Override
  public void visit(Animal a) {
    totalFare = totalFare + (a.getFare() * a.getNumberOfAnimals());
  }

  @Override
  public void visit(Luggage l) {
    totalFare = totalFare + (l.getFare() * l.getWeight());
  }

  public double getTotalFare() {
    return totalFare;
  }

  @SuppressWarnings("all")
  @Override
  public String toString() {
    return "Taxi(totalFare=" + this.getTotalFare() + ")";
  }

  @SuppressWarnings("all")
  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Taxi))
      return false;
    Taxi other = (Taxi)o;
    if (!other.canEqual((Object)this))
      return false;
    if (Double.compare(this.getTotalFare(), other.getTotalFare()) != 0)
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Taxi;
  }

  @SuppressWarnings("all")
  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    long $totalFare = Double.doubleToLongBits(this.getTotalFare());
    result = result * PRIME + (int)($totalFare >>> 32 ^ $totalFare);
    return result;
  }
}
