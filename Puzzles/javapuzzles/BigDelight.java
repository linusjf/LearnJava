package javapuzzles;

public enum BigDelight {
  ;
  private static final byte TARGET = (byte)0x90;
  private static final String JOY = "Joy!";

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  public static void main(String... args) {
    for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
      if (b == 0x90)
        System.out.println(JOY);
      if (b == (byte)0x90)
        System.out.println(JOY);
      if ((b & 0xff) == 0x90)
      System.out.println(JOY);
      if (b == TARGET)
        System.out.println(JOY);
    }
  }
}
