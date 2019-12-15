package flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Describe class <code>PenFactory</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class PenFactory { // NOPMD
  private static final Map<String, Pen> PENSMAP = new HashMap<>();

  private PenFactory() {
    throw new IllegalStateException("Private constructor");
  }

  /**
   * Describe <code>getThickPen</code> method here.
   *
   * @param color a <code>String</code> value
   * @return a <code>Pen</code> value
   */
  public static Pen getThickPen(String color) {
    String key = color + "-THICK";

    Pen pen = PENSMAP.get(key);

    if (pen != null) return pen;

    pen = new ThickPen();
    pen.setColor(color);
    PENSMAP.put(key, pen);

    return pen;
  }

  /**
   * Describe <code>getThinPen</code> method here.
   *
   * @param color a <code>String</code> value
   * @return a <code>Pen</code> value
   */
  public static Pen getThinPen(String color) {
    String key = color + "-THIN";

    Pen pen = PENSMAP.get(key);

    if (pen != null) return pen;

    pen = new ThinPen();
    pen.setColor(color);
    PENSMAP.put(key, pen);

    return pen;
  }

  /**
   * Describe <code>getMediumPen</code> method here.
   *
   * @param color a <code>String</code> value
   * @return a <code>Pen</code> value
   */
  public static Pen getMediumPen(String color) {
    String key = color + "-MEDIUM";

    Pen pen = PENSMAP.get(key);

    if (pen != null) return pen;

    pen = new MediumPen();
    pen.setColor(color);
    PENSMAP.put(key, pen);

    return pen;
  }
}
