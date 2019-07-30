package threads;

public enum FQNTest {
  ;
  public static void main(String[] args) {
    Thread[] threads = new Thread[5];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int j = 0; j < 10_000; j++) {
            try {
              Thread.sleep(1);
            } catch (InterruptedException e) {
              System.err.println(e);
            }
          }
        }
      }, "thread-" + i);
      threads[i].start();
    }
    try {
      for (Thread thread: threads) {
        thread.join();
      }
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
    System.out.println("[" + Thread.currentThread().getName()
                       + "] All threads have finished.");
  }
}
