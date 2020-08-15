package javapuzzlers;

public enum DoublePuzzle {
  ;

  public static void main(String... args) {
    Double negZero = Double.valueOf(-0.0d);

    Double posZero = Double.valueOf(+0.0d);

    System.out.println(negZero.doubleValue() == posZero.doubleValue());

    System.out.println(negZero.equals(posZero));

    Double nan1 = Double.NaN;
    Double nan2 = Double.NaN;

    System.out.println(nan1.doubleValue() == nan2.doubleValue());

    System.out.println(nan2.equals(nan1));
  }
}
