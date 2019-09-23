package player;

public final class ComputeEnduranceAlgorithm {
  private ComputeEnduranceAlgorithm() {
    throw new IllegalStateException("Private constructor");
  }
  
  public static int basicEndurance(int delta) {
    return 2 * delta;
  }

}
