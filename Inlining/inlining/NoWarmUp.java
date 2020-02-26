package inlining;

public class NoWarmUp {

  private NoWarmUp() {
    throw new IllegalStateException("Private constructor...");
  }

  public static void main(String[] args) {
    long start = System.nanoTime();
    ManualClassLoader.load();
    long end = System.nanoTime();
    System.out.println("Total time taken : " + (end - start));
  }
}
