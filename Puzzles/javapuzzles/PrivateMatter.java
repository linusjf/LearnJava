package javapuzzles;

@SuppressWarnings("PMD")
public enum PrivateMatter {
  ;
  public static void main(String... args) {
    System.out.println(((Base) new Derived()).className);
  }
}

@SuppressWarnings({ "PMD", "checkstyle:onetoplevelclass" })
class Base {
  public String className = "Base";
}

@SuppressWarnings({ "PMD", "checkstyle:onetoplevelclass" })
class Derived extends Base {
  private String className = "Derived";
}
