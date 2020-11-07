package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public final class Methodical {
  private static Class<String> stringclass = String.class;

  private Methodical() {
    throw new IllegalStateException("Private constructor invoked for class: "
                                    + getClass());
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String... args) {
    Method[] methods = stringclass.getMethods();

    // All methods for the String class
    for (Method method: methods) {
      System.out.println(
          "****************************************************");
      System.out.println("name: " + method.getName());
      System.out.println("defaultValue: " + method.getDefaultValue());
      System.out.println("generic return type: "
                         + method.getGenericReturnType());
      System.out.println("return type: " + method.getReturnType());
      System.out.println("modifiers: " + method.getModifiers());

      // Parameters
      Parameter[] parameters = method.getParameters();
      System.out.println(parameters.length + " parameters:");

      // also method.getParameterCount() is possible
      for (Parameter parameter: parameters) {
        System.out.println("parameter name: " + parameter.getName());
        System.out.println("parameter type: " + parameter.getType());
      }
      Class<?>[] parameterTypes = method.getParameterTypes();
      System.out.println(parameterTypes.length + " parameters:");
      for (Class<?> parameterType: parameterTypes) {
        System.out.println("parameter type name: " + parameterType.getName());
      }

      // Exceptions
      Class<?>[] exceptionTypes = method.getExceptionTypes();
      System.out.println(exceptionTypes.length + " exception types: ");
      for (Class<?> exceptionType: exceptionTypes) {
        System.out.println("exception name " + exceptionType.getName());
      }
      int modifiers = method.getModifiers();
      System.out.println(
          Modifier.toString(modifiers & Modifier.methodModifiers()));
      if (Modifier.isStatic(modifiers))
        System.out.println("is accessible: " + method.canAccess(null));
      else
        System.out.println("is accessible: "
                           + method.canAccess("String object"));
      System.out.println("is varArgs: " + method.isVarArgs());
    }
  }
}
