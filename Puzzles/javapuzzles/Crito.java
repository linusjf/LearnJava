package javapuzzles;

public enum Crito {
  ;
  @SuppressWarnings("PMD")
  public static void main(String... args) {
    CharSequence a = "21";
    String b = "21".substring(0);
    CharSequence c = "21";
    CharSequence d = new String("21");
    CharSequence e = ((String) d).substring(0);
    System.out.println(a == b);
    System.out.println(c == b);
    System.out.println(c == a);
    System.out.println(d == a);
    System.out.println(d == e);
  }
}
