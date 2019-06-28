package statepattern;

public final class OutForDelivery implements PackageState {
  // Singleton
  private static OutForDelivery instance = new OutForDelivery();

  private OutForDelivery() {
  }

  public static OutForDelivery getInstance() {
    return instance;
  }

  // Business logic and state transition
  @Override
  public void updateState(DeliveryContext ctx) {
    System.out.println("Package is out of delivery !!");
    ctx.setCurrentState(Delivered.getInstance());
  }
}
