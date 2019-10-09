package javapuzzles;

public enum ReferenceComparison {
  ;
  public static void main(String... args) {
    // Integer constructor deprecated in Java 9
    System.out.println(Integer.valueOf(0) == Integer.valueOf("0"));
  }
}
