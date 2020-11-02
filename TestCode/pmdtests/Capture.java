package pmdtests;

import java.util.function.Supplier;

public enum Capture {
  ;

  public static Supplier<Object> createObjectArray() {
    return new Object[0]::clone;
  }

  public static Supplier<Object> createObjectArray0() {
    Object[] o = new Object[0];
    return o::clone;
  }
}
