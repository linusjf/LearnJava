package javapuzzles;

public enum TextEditor {
  ;

  @SuppressWarnings("PMD.ShortClassName")
  static class Char {  
    char c;

    Char(char c) {
      this.c = c;
    }

    public void print() {
      System.out.print(c);
    }
  }

  public static void main(String[] args) {
    typeText(new Char('A'));

    typeText(new Char('B') {  
      @Override
      public void print() {
        System.out.print("[");
        super.print();  
        System.out.print("]");
      }
    });
  }

  private static void typeText(Char c) {
    c.print();
  }
}
