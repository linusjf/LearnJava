package javapuzzles;

import java.math.BigDecimal;

@SuppressWarnings("PMD")
public enum Change {
  ;

  public static void main(String[] args) {

    // 0.9 cannot be represented exactly by double or float
    System.out.println(2.00 - 1.10);

    // This only sokves the displaying
    System.out.printf("%.2f%n", 2.00 - 1.10);

    // This is one way of handling it
    System.out.println((200 - 110) + " cents");

    // This creates an exact value for 0.1 which is
    // not just 0.1
    System.out.println(new BigDecimal(2.00).subtract(new BigDecimal(1.10)));

    // This is the best way to do it; using the String constructor
    System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
  }
}
