package statepattern;

public final class InTransition implements PackageState {
  // Singleton
  private static InTransition instance = new InTransition();

  private InTransition() {}

  public static InTransition getInstance() {
    return instance;
  }

  // Business logic and state transition
  @Override
  public void updateState(DeliveryContext ctx) {
    System.out.println("Package is in transition !!");
    ctx.setCurrentState(OutForDelivery.getInstance());
  }
}
