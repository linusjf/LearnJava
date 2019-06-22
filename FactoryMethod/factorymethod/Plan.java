package factorymethod;

abstract class Plan {
  protected double rate;

  public abstract void allotRate();

  public void calculateBill(int units) {
    System.out.println(units * rate);
  }
}
