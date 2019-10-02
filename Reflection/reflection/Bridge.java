package reflection;

import java.lang.reflect.Method;
import java.math.BigInteger;

public enum Bridge {
  ;

  public static void main(String... args) {
    try {
      Class<? extends Object> bigInt = BigInteger.class;
      Method[] methods = bigInt.getMethods();
      System.out.println("Bridge Methods of BigInteger Class are");
      for (Method m: methods) {
        if (m.isBridge()) {
          System.out.println("Method: " + m.getName());
        }
      }
    } catch (SecurityException e) {
      System.err.println(e);
    }
  }
}
