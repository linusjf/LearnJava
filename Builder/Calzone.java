public class Calzone extends Pizza {
  private final boolean sauceInside;
  
  public static class Builder extends Pizza.Builder<Builder> {
    private boolean sauceInside = false; // Default
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
    sauceInside = builder.sauceInside;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Sauce = " + sauceInside + System.lineSeparator());
    sb.append("Toppings: " + System.lineSeparator());
    for (Topping t: toppings)
      sb.append(t + System.lineSeparator());
    return sb.toString();
  }
}
