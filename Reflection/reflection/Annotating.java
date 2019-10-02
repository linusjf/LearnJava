package reflection;

import java.lang.annotation.Annotation;

public enum Annotating {
  ;

  public static void main(String... args) {
    Class<ReflectableClass> object = ReflectableClass.class;
    // Retrieve all annotations from the class
    Annotation[] annotations = object.getAnnotations();
    for (Annotation annotation: annotations) {
      System.out.println(annotation);
    }
    // Checks if an annotation is present
    if (object.isAnnotationPresent(Reflectable.class)) {
      // Gets the desired annotation
      Annotation annotation = object.getAnnotation(Reflectable.class);
      System.out.println(annotation + " present in class "
                         + object.getTypeName());
      System.out.println(annotation + " present in class "
                         + object.getCanonicalName());
    }
  }
}
