package threads;

import java.util.concurrent.TimeUnit;
import threads.HelperClass.WaitingThread;

public enum IllegalMonitorStateNotifyExample {
  ;

  public static void main(String[] args) {
    try {
      Thread waitThread = new WaitingThread();

      // Start the execution.
      waitThread.start();

      // Sleep for some seconds.
      TimeUnit.SECONDS.sleep(5);

      // Try to notify the waiting thread without owning the synchronization
      // object. The following statement results in an
      // IllegalMonitorStateException.
      System.out.println("Checking for lock...");
      if (Thread.holdsLock(HelperClass.OBJ))
        HelperClass.OBJ.notifyAll();
      System.out.println("No exception since lock checked...");

      System.out.println("Caring a damn for lock ...");
      HelperClass.OBJ.notifyAll();

      // Wait for all threads to terminate.
      waitThread.join();
    } catch (InterruptedException ex) {
      System.err.println("An InterruptedException was caught: " + ex.getMessage());
      return;
    }
  }
}
