package javapuzzles;

import java.math.BigDecimal;

public enum EqualTo {
  ;

  public static void main(String... args) {
    floatingPoint();

    bigDecimalDouble();
    
    bigDecimalString();
  }

  private static void floatingPoint() {
    boolean isEqual = 0.1 + 0.2 == 0.3;
    System.out.println("0.1 + 0.2 == 0.3 ? " + isEqual);
  }

  private static void bigDecimalDouble() {
    BigDecimal first = new BigDecimal(0.1d);
    BigDecimal second = new BigDecimal(0.2d);
    boolean isEqual = first.add(second).equals(new BigDecimal(0.3d));
    System.out.println("BigDecimal (double): 0.1 + 0.2 == 0.3 ? " + isEqual);
  }
  
  private static void bigDecimalString() {
    BigDecimal first = new BigDecimal("0.1");
    BigDecimal second = new BigDecimal("0.2");
    boolean isEqual = first.add(second).equals(new BigDecimal("0.3"));
    System.out.println("BigDecimal (String): 0.1 + 0.2 == 0.3 ? " + isEqual);
  }
}
