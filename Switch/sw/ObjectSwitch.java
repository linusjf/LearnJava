package sw;

public final class ObjectSwitch {

  private ObjectSwitch() {
    throw new IllegalStateException("Illegal state.");
  }

  public static void main(String... args) {
    Integer i = Integer.getInteger("10");
    Long l = Long.getLong("10L");
    Double d = Double.valueOf(10.0);
    String s = "Hello";
    Object obj = new Object();
    System.out.println(formatterPatternSwitch(i));

    System.out.println(formatterPatternSwitch(l));
    System.out.println(formatterPatternSwitch(d));
    System.out.println(formatterPatternSwitch(s));
    System.out.println(formatterPatternSwitch(obj));
  }

  private static String formatterPatternSwitch(Object o) {
    return switch (o) {
        case Integer i -> String.format("int %d", i);
        case Long l    -> String.format("long %d", l);
        case Double d  -> String.format("double %f", d);
        case String s  -> String.format("String %s", s);
        default        -> o.toString();
    };
}
}
