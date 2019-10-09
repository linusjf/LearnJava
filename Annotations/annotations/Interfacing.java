package annotations;

public enum Interfacing {
  ;

  public static void main(String... args) {
    // implementing its methods
    @SuppressWarnings("unused")
    MyCustomInterface myFuncInterface = new MyCustomInterface() {
      @Override
      public int doSomething(int param) {
        return param * 10;
      }
    };
    System.out.println(myFuncInterface.doSomething(20));

    // clang-format off
    // using lambdas
    @SuppressWarnings("unused")
    MyCustomInterface myFuncInterfaceLambdas = x -> x * 10;

    // clang-format on
    System.out.println(myFuncInterfaceLambdas.doSomething(30));
  }
}
