package javapuzzles;

public class ThreadIon implements Runnable {

  @Override
  public void run() {
    System.out.println("1");
  }

  public static void main(String... args) {
    Thread t = new Thread(new ThreadIon());
    t.start();
    System.out.println("2");
    t.run();
    System.out.println("3");
  }
}
