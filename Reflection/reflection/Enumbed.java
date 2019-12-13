package reflection;

import java.lang.reflect.Field;

public final class Enumbed {

  enum ExampleEnum {
    ONE,
    TWO,
    THREE,
    FOUR;
  }

  private Enumbed() {
    throw new IllegalStateException("Private constructor");
  }
  
  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    ExampleEnum[] enumConstants = ExampleEnum.class.getEnumConstants();
    for (ExampleEnum exampleEnum: enumConstants)
      System.out.println("enum constant " + exampleEnum);
    Field[] flds = ExampleEnum.class.getDeclaredFields();
    for (Field f: flds)
      System.out.println(f.getName() + " " + f.isEnumConstant());
  }
}
