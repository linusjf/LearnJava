package javapuzzles;

public class Lazy {
  private static boolean initialized = false;
  
  static {
    Thread t = new Thread(new Runnable() {
      public void run() {
        initialized = true;
      }
    });
    t.start();
    try {
      // t.join() causes program to hang
      t.join(5000);
    } catch (InterruptedException e) {
      throw new AssertionError(e);
    }
  }

  public static void main(String[] args) {
    System.out.println(initialized);
  }
}
