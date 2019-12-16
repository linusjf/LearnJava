package javapuzzles;

public enum RunNull {
  ;

  public static void run(String str) {
    System.out.print("String");
  }

  public static void run(Object obj) {
    System.out.print("Object");
  }

  public static void main(String... args) {
    run(null);
  }
}
