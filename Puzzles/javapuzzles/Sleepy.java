package javapuzzles;

// https://twitter.com/heinzkabutz/status/1174714312151187456
@SuppressWarnings({ "PMD.NonStaticInitializer", "PMD.UnusedLocalVariable" })
public final class Sleepy {

  // clang-format off
  {
    System.out.println("Initializer block");
    Thread.sleep(1000);
  }

  // clang-format on
  private Sleepy() throws InterruptedException {
    throw new InterruptedException();
  }

  private Sleepy(String name) throws InterruptedException {
    this();
    System.out.println("Into constructor with name: " + name);
  }

  public static void main(String... args) {
    try {
      Sleepy sy = new Sleepy("sleepy");
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }
}
