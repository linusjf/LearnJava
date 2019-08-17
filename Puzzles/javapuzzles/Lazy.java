package javapuzzles;

public enum Lazy {
  ;
  private static boolean initialized;

  static {
    Thread t =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                initialized = true;
              }
            });
    t.start();
    try {
      // t.join() causes program to hang
      t.join();
    } catch (InterruptedException e) {
      throw new AssertionError(e);
    }
  }

  public static void main(String[] args) {
    System.out.println(initialized);
  }
}
