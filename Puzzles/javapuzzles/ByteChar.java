package javapuzzles;

public enum ByteChar {
  ;

  public static void main(String... args) {
    int n1 = (int)(byte)(char)-1;
    int n2 = (int)(char)(byte)-1;
    System.out.println(n1 == n2);
    System.out.printf("n1 = %d, n2 = %d%n", n1, n2);
  }
}
