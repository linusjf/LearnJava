package javapuzzles;

@SuppressWarnings("PMD")
public final class Dog extends Exception {
  public static final Dog INSTANCE = new Dog();

  private static final long serialVersionUID = 1L;

  private Dog() {
  }

  public String toString() {
    return "Woof";
  }

  private Object readResolve() {
    // Accept no substitutes!
    return INSTANCE;
  }
}
