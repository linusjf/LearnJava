package javapuzzles;

public enum GetString {
  ;

  @SuppressWarnings({"finally",
                     "PMD.ReturnFromFinallyBlock"})
  public static String
  getString() {
    try {
      return "tried";
    } finally {
      return "finalized";
    }
  }

  public static void main(String... args) {
    System.out.println(getString());
  }
}
