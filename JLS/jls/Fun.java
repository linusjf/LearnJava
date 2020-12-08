package jls;

@SuppressWarnings("all")
interface Fun<T, R> {
  R apply(T arg);
}

@SuppressWarnings("all")
class C {
  int size() {
    return 0;
  }

  int size(Object arg) {
    return 0;
  }

  int size(C arg) {
    return 0;
  }

  void test() {
    // OK: reference is to instance method size()
    Fun<C, Integer> f1 = C::size;
  }
}
