package annotations;

@FunctionalInterface
interface MyCustomInterface {
  /*
  * more abstract methods will cause the interface not to be a valid functional
       interface and the compiler will thrown an error:Invalid
      ’@FunctionalInterface’ annotation;
      FunctionalInterfaceAnnotation.MyCustomInterface is not a functional
  interface
  */
  // boolean isFunctionalInterface();
  int doSomething(int param);
}
