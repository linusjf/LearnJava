package jls;

@SuppressWarnings("PMD")
public final class TestInterfaceInit {
  @SuppressWarnings("all")
  private TestInterfaceInit() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String[] args) {
    System.out.println(J.i);
    System.out.println(K.j);
  }

  static int out(String s, int i) {
    System.out.println(s + "=" + i);
    return i;
  }
}

@SuppressWarnings("PMD")
interface I {
  int i = 1;
  int ii = TestInterfaceInit.out("ii", 2);
}

@SuppressWarnings("PMD")
interface J extends I {
  int j = TestInterfaceInit.out("j", 3);
  int jj = TestInterfaceInit.out("jj", 4);
}

@SuppressWarnings("PMD")
interface K extends J {
  int k = TestInterfaceInit.out("k", 5);
}
