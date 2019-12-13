package reflection;

import java.lang.annotation.Annotation;

public enum Annotating {
  ;

  public static void main(String... args) {
    Class<ReflectableClass> object = ReflectableClass.class;

    printAllAnnotations(object);
  }

  public static void printAllAnnotations(Class<?> object) {
    // Retrieve all annotations from the class
    Annotation[] annotations = object.getAnnotations();
    for (Annotation annotation: annotations)
      System.out.println(annotation);
    printAnnotationInClass(object, Reflectable.class);
  }

  private static void printAnnotationInClass(
      Class<?> object,
      Class<? extends Reflectable> annotationClass) {
    // Checks if an annotation is present
    if (object.isAnnotationPresent(annotationClass)) {
      // Gets the desired annotation
      Annotation annotation = object.getAnnotation(annotationClass);
      System.out.println(annotation + " present in class "
                         + object.getTypeName());
      System.out.println(annotation + " present in class "
                         + object.getCanonicalName());
    }
  }
}
