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
    SMALL, MEDIUM, LARGE;
  }

  private final Size size;

  private NyPizza(Builder builder) {
    super(builder);
    this.size = builder.size; // NOPMD
  }

  /**
   * describes object state as string.
   */
  @Override
  String describe() {
    String ls = System.lineSeparator();
    StringBuilder sb = new StringBuilder(36);
    sb.append("Size = ").append(size).append(ls).append("Toppings: ").append(ls);
    for (Topping t : toppings) sb.append(t).append(ls);
    return sb.toString();
  }

  /**
   * returns object state as string.
   */
  @Override
  public String toString() {
    return describe();
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof NyPizza)) return false;
    NyPizza other = (NyPizza) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$size = this.size;
    Object other$size = other.size;
    if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof NyPizza;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $size = this.size;
    result = result * PRIME + ($size == null ? 43 : $size.hashCode());
    return result;
  }

  /**
   * Builder class that builds the Pizza object using the Fluent API style.
   */
  public static class Builder extends Pizza.Builder<Builder> {
    private final Size size;

    /**
     * Constructor.
     *
     * @param size Size of pizza.
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

}
