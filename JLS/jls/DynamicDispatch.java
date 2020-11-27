package jls;

@SuppressWarnings("PMD")
public class DynamicDispatch extends Super {
  // That is, 3
  int three = (int)Math.PI;

  void printThree() {
    System.out.println(three);
  }

  public static void main(String[] args) {
    DynamicDispatch t = new DynamicDispatch();
    t.printThree();
  }
}

@SuppressWarnings("PMD")
class Super {
  Super() {
    printThree();
  }

  void printThree() {
    System.out.println("three");
  }
}
