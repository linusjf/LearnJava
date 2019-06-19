package builder;

/**
 * Describe class <code>Calzone</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Calzone extends Pizza {
  private final boolean sauceInside;

  private Calzone(Builder builder) {
    super(builder);
    this.sauceInside = builder.sauceInside;  // NOPMD
  }

  /** returns object state as String. */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(30);
    sb.append("Sauce = ")
        .append(sauceInside)
        .append(System.lineSeparator())
        .append("Toppings: ")
        .append(System.lineSeparator());
    for (Topping t : toppings)
      sb.append(t).append(System.lineSeparator());
    return sb.toString();
  }

  /** Inner class that builds the Calzone object. */
  public static class Builder extends Pizza.Builder<Builder> {
    private boolean sauceInside;  // Default

    /**
     * sets sauce.
     *
     * @return Calzone object
     */
    public Builder sauceInside() {
      sauceInside = true;
      return this;
    }

    @Override
    public Calzone build() {
      return new Calzone(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
