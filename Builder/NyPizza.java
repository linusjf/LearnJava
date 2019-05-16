import java.util.Objects;
public class NyPizza extends Pizza {
	
  public enum Size 
  { 
    SMALL, 
    MEDIUM, 
    LARGE
  }
	
  private final Size size;

	public static class Builder extends Pizza.Builder<Builder> {
		private final Size size;

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
		size = builder.size;
	}

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Size = " + size + System.lineSeparator());
    sb.append("Toppings: " + System.lineSeparator());
    for (Topping t: toppings)
      sb.append(t + System.lineSeparator());
    return sb.toString();
  }
}
