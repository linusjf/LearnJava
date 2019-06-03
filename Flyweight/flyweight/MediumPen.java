package flyweight;

/**
 * Describe class <code>MediumPen</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class MediumPen implements Pen {
  private final BrushSize brushSize = BrushSize.MEDIUM; // NOPMD
  private String color = null;

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
    System.out.println("Drawing MEDIUM content in color : " + color);
  }
}
