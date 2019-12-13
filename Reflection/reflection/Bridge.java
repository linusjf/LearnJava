package reflection;

import java.lang.reflect.Method;
import java.math.BigInteger;

public enum Bridge {
  ;

  public static void main(String... args) {
    try {
      Class<? extends Object> bigInt = BigInteger.class;
      printBridgeMethods(bigInt);
      Class<? extends Object> stringClass = String.class;
      printBridgeMethods(stringClass);
    } catch (SecurityException e) {
      System.err.println(e);
    }
  }

  private static void printBridgeMethods(Class<? extends Object> obj) throws SecurityException {
      Method[] methods = obj.getMethods();
      System.out.println("Bridge Methods of "
          + obj + " are");
      for (Method m: methods) {
        if (m.isBridge()) 
          System.out.println("Method: " + m.getName());
      }
  }
}
