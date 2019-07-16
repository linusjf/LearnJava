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
      HelperClass.OBJ.notifyAll();

      // Wait for all threads to terminate.
      waitThread.join();
    } catch (InterruptedException ex) {
      System.err.println("An InterruptedException was caught: "
                         + ex.getMessage());
    }
  }
}
