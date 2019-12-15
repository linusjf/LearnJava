package reflection;

public class HelloWorld {

  public void printName() {
    System.out.println(HelloWorld.class);
  }

  public static void main(String... args) {
    (new HelloWorld()).printName();
  }
}
