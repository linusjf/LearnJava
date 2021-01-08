package threads;

@SuppressWarnings("PMD.SystemPrintln")
public enum IllegalMonitorStateWaitExample {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      // Try to wait on the synchronization object, without owning it.
      System.out.println("Checking for lock...");

      // The following statement results in an IllegalMonitorStateException.
      if (Thread.holdsLock(HelperClass.OBJ))
        HelperClass.OBJ.wait(1000);
      System.out.println("No exception since lock checked...");

      System.out.println("Caring a damn for lock ...");

      // The following statement results in an IllegalMonitorStateException.
      HelperClass.OBJ.wait(1000);
    } catch (InterruptedException ex) {
      System.err.println("An InterruptedException was caught: "
                         + ex.getMessage());
    }
  }
}
