package statepattern;

public final class Delivered implements PackageState {
  // Singleton
  private static Delivered instance = new Delivered();

  private Delivered() {}

  public static Delivered getInstance() {
    return instance;
  }

  // Business logic
  @Override
  public void updateState(DeliveryContext ctx) {
    System.out.println("Package is delivered!!");
  }
}
