package flyweight;

/**
 * Describe interface <code>Pen</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public interface Pen {
  enum BrushSize {
    THIN,
    MEDIUM,
    THICK,
    ;
  }

  /**
   * Describe <code>setColor</code> method here.
   *
   * @param color a <code>String</code> value
   */
  void setColor(String color);

  /**
   * Describe <code>draw</code> method here.
   *
   * @param content a <code>String</code> value
   */
  void draw(String content);
}
