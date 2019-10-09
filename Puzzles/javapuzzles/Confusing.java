package javapuzzles;

public final class Confusing {

  private Confusing(Object o) {
    System.out.println("Object " + o);
  }

  private Confusing(double... array) {
    System.out.println("double array " + array);
  }

  public static void main(String[] args) {
    new Confusing(null);
  }
}
