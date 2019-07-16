package threads;

import java.util.concurrent.TimeUnit;

public enum HelperClass {
  ;
  // The Object used for synchronization among threads.
  public static final Object OBJ = new Object();

  public static class WaitingThread extends Thread {

    @Override
    public void run() {
      synchronized (OBJ) {
        try {
          System.out.println("[WaitingThread]: Waiting for another thread "
                             + "to notify me...");
          OBJ.wait();
          System.out.println("[WaitingThread]: Successfully notified!");
        } catch (InterruptedException ex) {
          System.err.println(
              "[WaitingThread]: An InterruptedException was caught: "
              + ex.getMessage());
        }
      }
    }
  }

  public static class WakingThread extends Thread {
    @Override
    public void run() {
      synchronized (OBJ) {
        try {
          System.out.println("[WakingThread]: Sleeping for some time...");
          TimeUnit.SECONDS.sleep(5);
          System.out.println("[WakingThread]: Woke up!");

          System.out.println(
              "[WakingThread]: About to notify another thread...");
          OBJ.notifyAll();
          System.out.println(
              "[WakingThread]: Successfully notified some other thread!");
        } catch (InterruptedException ex) {
          System.err.println(
              "[WaitingThread]: An InterruptedException was caught: "
              + ex.getMessage());
        }
      }
    }
  }
}
