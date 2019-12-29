package javapuzzles;

public enum IntCache {
  ;

  @SuppressWarnings("PMD.CompareObjectsWithEquals")
  public static void main(String... args) {
    for (int i = -128; i < 128; i++) {
      Integer a = Integer.valueOf(i);
      Integer b = Integer.valueOf(i);
      System.out.printf("%d : " + (a == b) + "%n", i);
    }
    for (int i = 128; i < 256; i++) {
      Integer a = Integer.valueOf(i);
      Integer b = Integer.valueOf(i);
      System.out.printf("%d : " + (a == b) + "%n", i);
    }
    for (int i = -256; i < -128; i++) {
      Integer a = Integer.valueOf(i);
      Integer b = Integer.valueOf(i);
      System.out.printf("%d : " + (a == b) + "%n", i);
    }
  }
}
