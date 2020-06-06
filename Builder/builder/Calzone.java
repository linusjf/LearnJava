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
    this.sauceInside = builder.sauce;
  }

  /** describes object state as String. */
  @Override
  String describe() {
    String ls = System.lineSeparator();
    StringBuilder sb = new StringBuilder(30);
    sb.append("Sauce = ")
        .append(sauceInside)
        .append(ls)
        .append("Toppings: ")
        .append(ls);
    for (Topping t: toppings)
      sb.append(t).append(ls);
    return sb.toString();
  }

  /** returns object state as String. */
  @Override
  public String toString() {
    return describe();
  }

  /** Inner class that builds the Calzone object. */
  public static class Builder extends Pizza.Builder<Builder> {
    private boolean sauce;

    /**
     * sets sauce.
     *
     * @return Calzone object
     */
    public Builder sauceInside() {
      sauce = true;
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
