package annotations;

public enum TestInheritedAnnotations {
  ;

  public static void main(String... args) {
    System.out.println("is true: "
                       + AnnotatedSuperClass.class.isAnnotationPresent(
                           InheritedAnnotation.class));
    System.out.println("is true: "
                       + AnnotatedSubClass.class.isAnnotationPresent(
                           InheritedAnnotation.class));
    System.out.println("is true: "
                       + AnnotatedInterface.class.isAnnotationPresent(
                           InheritedAnnotation.class));
    System.out.println("is true: "
                       + AnnotatedImplementedClass.class.isAnnotationPresent(
                           InheritedAnnotation.class));
  }
}
