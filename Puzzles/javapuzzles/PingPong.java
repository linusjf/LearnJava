package javapuzzles;

public enum PingPong {
  ;

  @SuppressWarnings("PMD.DontCallThreadRun")
  public static void main(String[] a) {
    synchronized (PingPong.class) {
      Thread t = new Thread() {
        @Override
        public void run() {
          pong();
        }
      };
      t.run();
      System.out.printf("Ping%n");
      System.out.printf("Ping%n");
      t.start();
    }
  }

  static void pong() {
    synchronized (PingPong.class) {
      System.out.printf("Pong%n");
    }
  }
}
