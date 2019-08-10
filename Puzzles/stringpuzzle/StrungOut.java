package stringpuzzle;

@SuppressWarnings("PMD")
public enum StrungOut {
  ;
  public static void main(java.lang.String... args) {
    String s = new String("Hello world");
    System.out.println(s);
  }
}

@SuppressWarnings("checkstyle:onetoplevelclass")
class String {
  private final java.lang.String s;

  String(java.lang.String s) {
    this.s = s;
  }

  @Override
  public java.lang.String toString() {
    return s;
  }
}
