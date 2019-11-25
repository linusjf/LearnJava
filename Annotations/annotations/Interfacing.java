package annotations;

public enum Interfacing {
  ;

  @SuppressWarnings({"unused","PMD.DataflowAnomalyAnalysis"})
  public static void main(String... args) {
    // implementing its methods
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
