package jls;

@SuppressWarnings("all")
public class NullStatic {
  static String mountain = "Chocorua";

  static NullStatic favorite() {
    System.out.print("Mount ");
    return null;
  }

  public static void main(String[] args) {
    System.out.println(favorite().mountain);
  }
}
