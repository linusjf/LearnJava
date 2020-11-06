package javapuzzles;

enum HelloEnum {
  ;

  HelloEnum() {
    throw new IllegalStateException("Hello");
  }
}
