package javapuzzles;

public enum SelfInterruption {
  ;

  public static void main(String[] args) {
    Thread.currentThread().interrupt();
    if (Thread.interrupted()) System.out.println("Interrupted: " + Thread.interrupted());
    else System.out.println("Not interrupted: " + Thread.interrupted());
    altMain(args);
  }

  public static void altMain(String... args) {
    Thread.currentThread().interrupt();
    if (Thread.currentThread().isInterrupted())
      System.out.println("Interrupted: " + Thread.currentThread().isInterrupted());
    else System.out.println("Not interrupted: " + Thread.currentThread().isInterrupted());
  }
}
