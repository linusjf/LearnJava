package pmdtests;

@SuppressWarnings("checkstyle:magicnumber")
public enum FQNTest {
  ;

  public static void main(String[] args) {
    Thread[] pmdtests = new Thread[5];
    for (int i = 0; i < pmdtests.length; i++) {
      pmdtests[i] = new Thread(new Runnable() {
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
      pmdtests[i].start();
    }
    try {
      for (Thread thread: pmdtests) {
        thread.join();
      }
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
    System.out.println("[" + Thread.currentThread().getName()
                       + "] All threads have finished.");
  }
}
