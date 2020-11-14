package javapuzzles;

enum EnumPrivate {
  INSTANCE;

  EnumPrivate() {
    throw new IllegalStateException("Hello");
  }

  public void sayHello() {
    System.out.println("Hello!");
  }

  public static void main(String... args) {
    try {
      System.out.println("Invoking hello enum");
      EnumPrivate hello = EnumPrivate.INSTANCE;
      hello.sayHello();
    } catch (ExceptionInInitializerError eie) {
      System.err.println(eie.getMessage());
      System.err.println(eie.getCause());
    }
  }
}
