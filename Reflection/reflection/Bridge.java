package reflection;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.logging.Logger;

public enum Bridge {
  ;
  private static final Logger LOGGER = Logger.getLogger(Bridge.class.getName());

  public static void main(String... args) {
    try {
      Class<? extends Object> bigInt = BigInteger.class;
      printBridgeMethods(bigInt);
      Class<? extends Object> stringClass = String.class;
      printBridgeMethods(stringClass);
    } catch (SecurityException e) {
      LOGGER.severe(e.getMessage());
    }
  }

  @SuppressWarnings("PMD.SystemPrintln")
  private static void printBridgeMethods(Class<? extends Object> obj) {
    Method[] methods = obj.getMethods();
    System.out.println("Bridge Methods of " + obj + " are");
    for (Method m: methods) {
      if (m.isBridge())
        System.out.println("Method: " + m.getName());
    }
  }
}
