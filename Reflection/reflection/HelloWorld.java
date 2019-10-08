package reflection;

public class HelloWorld {
  public void printName() {
    System.out.println(this.getClass().getName());
  }

  public static void main(String... args) {
    (new HelloWorld()).printName();
  }
}
