package flyweight;

public class ThinPen implements Pen {
  private final BrushSize brushSize = BrushSize.THIN;  // NOPMD
  private String color;

  /**
   * Describe <code>setColor</code> method here.
   *
   * @param color a <code>String</code> value
   */
  @Override
  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public void draw(String content) {
    System.out.println("Drawing THIN content in color : " + color);
    System.out.println("Drawing THIN content with brush size : " + brushSize);
  }
}
