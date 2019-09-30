package reflection;

import java.lang.reflect.Field;

public final class Enumbed {

  enum ExampleEnum { ONE, TWO, THREE, FOUR }

  private Enumbed() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    ExampleEnum value = ExampleEnum.FOUR;
    ExampleEnum[] enumConstants = value.getClass().getEnumConstants();
    for (ExampleEnum exampleEnum: enumConstants)
      System.out.println("enum constant " + exampleEnum);
    Field[] flds = value.getClass().getDeclaredFields();
    for (Field f: flds)
      System.out.println(f.getName() + " " + f.isEnumConstant());
  }
}
