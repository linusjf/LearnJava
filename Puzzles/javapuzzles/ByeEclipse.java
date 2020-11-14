package javapuzzles;

@SuppressWarnings("PMD.SystemPrintln")
public enum ByeEclipse {
  ;

  @SuppressWarnings({"PMD.AssignmentInOperand", "PMD.DataflowAnomalyAnalysis"})
  public static void main(String... args) {
    boolean b1 = true;
    boolean b2 = false;
    if ((b1 = false) | (b1 ^ b2)) {
      System.out.println("Eclipse.");
      return;
    }
    System.out.println("Bye.");
  }
}
