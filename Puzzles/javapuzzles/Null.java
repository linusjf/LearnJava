package javapuzzles;

public enum Null {
  ;

  public static void greet() {
    System.out.println("Hello world!");
  }

  @SuppressWarnings("static")
  public static void main(String[] args) {
    ((Null)null).greet();
  }
}
