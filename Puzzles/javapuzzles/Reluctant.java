package javapuzzles;

@SuppressWarnings("PMD")
public class Reluctant {
  private Reluctant internalInstance = new Reluctant();

  public Reluctant() throws Exception {
    throw new Exception("I’m not coming out");
  }

  public static void main(String[] args) {
    try {
      Reluctant b = new Reluctant();
      System.out.println("Surprise!");
    } catch (Exception | Error ex) {
      System.err.println("StackOverflowError caught: " + ex.getMessage());
      System.out.println("I told you so");
    }
  }
}
