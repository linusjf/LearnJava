package javapuzzles;

public enum Tip {
  ;
  
  public static void main(String... args) {
    Point p = new Point();
    System.out.println(p.x++ + ", " + ++p.y);
    System.out.println(p.x++ + ", " + p.y++);
    System.out.println(++p.x + ", " + ++p.y);
    System.out.println(++p.x + ", " + p.x++);
    System.out.println(p.y++ + ", " + ++p.y);
  }

  static class Point {
    protected int x = 3;
    protected int y = 5;

    public Point() {
      super();
    }

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
