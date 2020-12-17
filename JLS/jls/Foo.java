package jls;

enum Foo {
  A;
  private final Object obj = new Object();
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSomething() {
    synchronized (obj) {
      // synchronized? If the enum constant is really immutable, then no data race can occur
      // So the synchronized hints that there is something fishy... or at least it is redundant
      return null;
    }
  }

  public void doSomething() {
  }

  void onlyMe(Foo f) {
    synchronized (f) {
      doSomething();
    }
  }
}
