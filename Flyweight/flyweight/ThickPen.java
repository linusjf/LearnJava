package flyweight;

public class ThickPen implements Pen {

  final BrushSize brushSize = BrushSize.THICK; // intrinsic state - shareable
  private String color = null;
  // extrinsic state - supplied by client

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public void draw(String content) {
    System.out.println("Drawing THICK content in color : " + color);
  }
}
