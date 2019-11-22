package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public final class Construct {

  private Construct() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    // get all visible constructors
    Constructor<?>[] constructors = String.class.getConstructors();

    // all constructors
    Constructor<?>[] declaredConstructors =
        String.class.getDeclaredConstructors();
    System.out.println("Declared constructors...");
    for (Constructor<?> constructor: declaredConstructors) {
      int numberParams = constructor.getParameterCount();
      System.out.println("constructor " + constructor.getName());
      System.out.println("number of arguments " + numberParams);

      // public, private, etc.
      int modifiersConstructor = constructor.getModifiers();
      System.out.println(
          "modifiers "
          + Modifier.toString(modifiersConstructor
                              & Modifier.constructorModifiers()));

      // array of parameters, more info in the methods section
      Parameter[] parameters = constructor.getParameters();
      System.out.println(parameters.length + " parameters:");

      // also method.getParameterCount() is possible
      for (Parameter parameter: parameters) {
        System.out.println("parameter name: " + parameter.getName());
        System.out.println("parameter type: " + parameter.getType());
      }

      // annotations array, more info in the annotations section
      Annotation[] annotations = constructor.getAnnotations();
      if (annotations.length > 0) {
        System.out.println("Annotations: ");
        for (Annotation anno: annotations)
          System.out.println(anno.annotationType());
      }
    }
  }
}
