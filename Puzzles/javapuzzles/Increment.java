package javapuzzles;

public enum Increment {
  ;

  public static void main(String... args) {
    int j = 0;
    for (int i = 0; i < 100; i++) j = j++;
    System.out.println(j);
    j = 0;
    for (int i = 0; i < 100; i++) j = j--;
    System.out.println(j);
    j = 0;
    for (int i = 0; i < 100; i++) j = ++j;
    System.out.println(j);
    j = 0;
    for (int i = 0; i < 100; i++) j = --j;
    System.out.println(j);
  }
}
