package builder;

/**
 * Describe class <code>Calzone</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Calzone extends Pizza {
  private final boolean sauceInside;

  /** Inner class that builds the Calzone object. */
  public static class Builder extends Pizza.Builder<Builder> {

    private boolean sauceInside = false; // Default

    /** sets sauce. */
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

  private Calzone(Builder builder) {
    super(builder);
    this.sauceInside = builder.sauceInside;
  }

  /** returns object state as String. */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Sauce = " + sauceInside + System.lineSeparator());
    sb.append("Toppings: " + System.lineSeparator());
    for (Topping t : toppings) sb.append(t + System.lineSeparator());
    return sb.toString();
  }
}
