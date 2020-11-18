package jls;

@SuppressWarnings("PMD")
public class ReceiverParameters {
  // No receiver parameter is permitted in the constructor of
  // a top level class, as there is no conceivable type or name.
  ReceiverParameters(/* ?? ?? */) {
    // empty constructor
  }

  // OK: receiver parameter in an instance method
  void method(ReceiverParameters this) {
    // empty method
  }

  // Illegal: receiver parameter in a static method
  // static void nethod(ReceiverParameters this) {
  // empty method
  // }

  // OK: the receiver parameter represents the instance
  // of Test which immediately encloses the instance
  // of A being constructed.
  class A {
    A(ReceiverParameters ReceiverParameters.this) {
      // empty constructor
    }

    // OK: the receiver parameter represents the instance
    // of A for which A.m() is invoked.
    void method(A this) {
      // empty method
    }

    class B {
      // OK: the receiver parameter represents the instance
      // of A which immediately encloses the instance of B
      // being constructed.
      B(ReceiverParameters.A A.this) {
        // empty constructor
      }

      // OK: the receiver parameter represents the instance
      // of B for which B.m() is invoked.
      void method(ReceiverParameters.A.B this) {
        // empty method
      }
    }
  }
}
