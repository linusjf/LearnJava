package builder;

import java.util.Objects;

/**
 * Describe class <code>NyPizza</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class NyPizza extends Pizza {
  public enum Size {
    SMALL,
    MEDIUM,
    LARGE
  }

  private final Size size;

  private NyPizza(Builder builder) {
    super(builder);
    this.size = builder.size; // NOPMD
  }

  /** returns object state as string. */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(36);
    sb.append("Size = ")
        .append(size)
        .append(System.lineSeparator())
        .append("Toppings: ")
        .append(System.lineSeparator());
    for (Topping t : toppings) sb.append(t).append(System.lineSeparator());
    return sb.toString();
  }

  /** Builder class that builds the Pizza object using the Fluent API style. */
  public static class Builder extends Pizza.Builder<Builder> {
    private final Size size;

    /**
     * Constructor.
     *
     * @param size Size of pizza.
     */
    public Builder(Size size) {
      super();
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
}
