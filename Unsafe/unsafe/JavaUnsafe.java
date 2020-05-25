package unsafe;

import static unsafe.UnsafeUtils.*;

public enum JavaUnsafe {
  ;

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public static void main(String[] args) throws ReflectiveOperationException {
    System.out.println("The address size is: " + getUnsafe().addressSize());
  }
}
