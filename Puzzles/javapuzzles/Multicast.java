package javapuzzles;

public enum Multicast {
  ;

  public static void main(String[] args) {
    byte b = -1;
    System.out.println(b);
    System.out.println((char) b);
    System.out.println((int) (char) b);

    // no sign extension
    int i = b & 0xffff;
    System.out.println(i);

    // sign extension
    i = (int) b;
    System.out.println(i);

    // sign extension all 16 bits set
    char c = (char) b;
    System.out.println(c);

    // sign extension not performed
    i = c;
    System.out.println(i);

    // sign extension
    i = (short) c;
    System.out.println(i);
  }
}
