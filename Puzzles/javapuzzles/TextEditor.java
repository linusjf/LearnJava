package javapuzzles;

public class TextEditor {
  static class Char {  // line 1
    char c;

    public Char(char c) {
      this.c = c;
    }

    public void print() {
      System.out.print(c);
    }
  }

  public static void main(String[] args) {
    typeText(new Char('A'));

    typeText(new Char('B') {  // line 2
      public void print() {
        System.out.print("[");
        super.print();  // line 3
        System.out.print("]");
      }
    });
  }

  private static void typeText(Char c) {
    c.print();
  }
}
