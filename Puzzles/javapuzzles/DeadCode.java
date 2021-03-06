package javapuzzles;

public final class DeadCode {

  private DeadCode() {

    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    int k = 1;
    if (k == 0)
      System.out.println("This statement is never executed.\n");
  }
}
