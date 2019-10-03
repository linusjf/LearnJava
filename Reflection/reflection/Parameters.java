package reflection;

import java.lang.reflect.Parameter;
import java.lang.reflect.Method;

public enum Parameters {
  ;
  public static void main(String... args) {

    Class<String> stringClass = String.class;
    for (Method methodStringClass: stringClass.getDeclaredMethods()) {
      System.out.println("method " + methodStringClass.getName());
      for (Parameter paramMethodStringClass:
           methodStringClass.getParameters()) {
        // arg0, arg1, etc because the eclipse compiling tool (different ←-
        // not support -parameters option yet
        System.out.println(" parameter name "
                           + paramMethodStringClass.getName());
        System.out.println(" parameter type "
                           + paramMethodStringClass.getType());
      }
    }
  }
}
