package javapuzzles;

@SuppressWarnings("PMD")
public enum ShadesOfGray {
  ;

  public static void main(String[] args) {
    System.out.println(X.Y.zed);
    System.out.println(Ex.Why.z);
  }
}

@SuppressWarnings({"PMD", "checkstyle:onetoplevelclass"})
enum X {
  ;
  // clang-format off
  static class Y {
    static String zed = "Black";
  }  // clang-format on

  static C why = new C();
}

@SuppressWarnings({"PMD", "checkstyle:onetoplevelclass"})
enum Ex {
  ;
  static See y = new See();

  // clang-format off
  static class Why {
    static String z = "Black";
  }
  // clang-format on
}

@SuppressWarnings({"PMD", "checkstyle:onetoplevelclass"})
class See {
  String z = "White";
}

@SuppressWarnings({"PMD", "checkstyle:onetoplevelclass"})
class C {
  String Z = "White";
}
