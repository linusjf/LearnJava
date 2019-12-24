package javapuzzles;

public class Theaetetus {
  @SuppressWarnings("static")
  public static void main(String... args) {
    new Theaetetus().printIt((String) null);
    new Theaetetus().printIt((Integer) null);
  }

  public static void printIt(String s) {
    System.out.print("String");
  }

  public void printIt(Integer i) {
    System.out.print("Integer");
  }
}
