package bridge;

@SuppressWarnings("PMD.ShortClassName")
class Bike extends Vehicle {
  Bike(Workshop workShop1, Workshop workShop2) {
    super(workShop1, workShop2);
  }

  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void manufacture() {
    System.out.print("Bike ");
    workShop1.work();
    workShop2.work();
  }
}
