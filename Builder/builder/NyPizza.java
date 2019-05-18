package builder;

import java.util.Objects;

/**
 * Describe class <code>NyPizza</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class NyPizza extends Pizza {

  public enum Size {
    SMALL,
    MEDIUM,
    LARGE
  }

  private final Size size;

  /** Builder class that builds the Pizza object
   * using the Fluent API style.
   */
     
  public static class Builder extends Pizza.Builder<Builder> {

    private final Size size;

    /**
     * Constructor.
     */
    public Builder(Size size) {
      this.size = Objects.requireNonNull(size);
    }

    @Override
    public NyPizza build() {
      return new NyPizza(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

  private NyPizza(Builder builder) {
    super(builder);
    this.size = builder.size;
  }

  /**
   * returns object state as string.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Size = " + size + System.lineSeparator());
    sb.append("Toppings: " + System.lineSeparator());
    for (Topping t : toppings)
      sb.append(t + System.lineSeparator());
    return sb.toString();
  }
}
