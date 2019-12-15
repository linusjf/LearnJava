package javapuzzles;

public enum ReturnFinally {
  ;
  @SuppressWarnings("PMD")
  public static String getString() {
    try {
      return "A";
    } finally {
      return "1";
    }
  }

  public static void main(String... args) {
    System.out.println(getString());
  }
}
