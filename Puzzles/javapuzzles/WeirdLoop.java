package javapuzzles;

public class WeirdLoop {
  private static final int i = 99;

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {  // line n1
      System.out.print(i);
      i++;  // line n2
      break;
    }
    System.out.print(i);
  }
}
