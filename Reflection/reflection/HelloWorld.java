package reflection;

public class HelloWorld {
  @SuppressWarnings("PMD.SystemPrintln")
  public void printName() {
    System.out.println(HelloWorld.class);
  }

  public static void main(String... args) {
    (new HelloWorld()).printName();
  }
}
