package javapuzzles;

public enum JavaHungry {
  ;

  @SuppressWarnings({"PMD.DoNotCallSystemExit",
                     "PMD.AvoidCatchingGenericException"})
  public static void
  main(String[] args) {
    try {
      System.out.println("Inside try block ");
      /* After executing below line
      jvm terminates the program */
      System.exit(0);
    } catch (Exception e) {
      System.err.println("Inside catch block");
    } finally {
      System.out.println("Inside finally block");
    }
  }
}
