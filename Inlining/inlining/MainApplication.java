package inlining;

public class MainApplication {

  private MainApplication() {
    throw new IllegalStateException("Private constructor...");
  }

  static {
    long start = System.nanoTime();
    ManualClassLoader.load();
    long end = System.nanoTime();
    System.out.println("Warm Up time : " + (end - start));
  }

  public static void main(String[] args) {
    long start = System.nanoTime();
    ManualClassLoader.load();
    long end = System.nanoTime();
    System.out.println("Total time taken : " + (end - start));
  }
}
