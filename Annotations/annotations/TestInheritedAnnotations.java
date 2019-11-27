package annotations;

public enum TestInheritedAnnotations {
  ;

  private static final String IS_TRUE = "is true: ";

  public static void main(String... args) {
    System.out.println(
        IS_TRUE + AnnotatedSuperClass.class.isAnnotationPresent(InheritedAnnotation.class));
    System.out.println(
        IS_TRUE + AnnotatedSubClass.class.isAnnotationPresent(InheritedAnnotation.class));
    System.out.println(
        IS_TRUE + AnnotatedInterface.class.isAnnotationPresent(InheritedAnnotation.class));
    System.out.println(
        IS_TRUE + AnnotatedImplementedClass.class.isAnnotationPresent(InheritedAnnotation.class));
  }
}
