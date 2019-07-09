package statepattern;

public final class Shipped implements PackageState {
  // Singleton
  private static Shipped instance = new Shipped();

  private Shipped() {
  // empty constructor
  }

  public static Shipped getInstance() {
    return instance;
  }

  // Business logic and state transition
  @Override
  public void updateState(DeliveryContext ctx) {
    System.out.println("Package is shipped !!");
    ctx.setCurrentState(InTransition.getInstance());
  }
}
