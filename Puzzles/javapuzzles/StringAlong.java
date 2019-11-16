package javapuzzles;

public enum StringAlong {
  ;

  public static void main(String... args) {
    CharSequence a = "21";
    String b = "21".substring(0);
    String c = new String("21");
    String d = c.substring(0);
    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a == d);
    System.out.println(c == d);
  }
}
