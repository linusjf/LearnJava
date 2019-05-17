package builder;
// Builder Pattern
/**
 * Describe class <code>NutritionFacts</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  /**
   * Describe class <code>Builder</code> here.
   *
   */
  public static class Builder {
    // Required parameters
    private final int servingSize;
    private final int servings;
    // Optional parameters - initialized to default values
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    /**
     * Creates a new <code>Builder</code> instance.
     *
     * @param servingSize an <code>int</code> value
     * @param servings an <code>int</code> value
     */
    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    /**
     * Describe <code>calories</code> method here.
     *
     * @param val an <code>int</code> value
     * @return a <code>Builder</code> value
     */
    public Builder calories(int val) {
      calories = val;
      return this;
    }

    /**
     * Describe <code>fat</code> method here.
     *
     * @param val an <code>int</code> value
     * @return a <code>Builder</code> value
     */
    public Builder fat(int val) {
      fat = val;
      return this;
    }

    /**
     * Describe <code>sodium</code> method here.
     *
     * @param val an <code>int</code> value
     * @return a <code>Builder</code> value
     */
    public Builder sodium(int val) {
      sodium = val;
      return this;
    }

    /**
     * Describe <code>carbohydrate</code> method here.
     *
     * @param val an <code>int</code> value
     * @return a <code>Builder</code> value
     */
    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }

    /**
     * Describe <code>build</code> method here.
     *
     * @return a <code>NutritionFacts</code> value
     */
    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }

  /**
   * Describe <code>toString</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("serving size = " + servingSize)
        .append(", servings = " + servings)
        .append(", calories = " + calories)
        .append(", fat = " + fat)
        .append(", sodium = " + sodium)
        .append(", carbohydrates = " + carbohydrate);
    return sb.toString();
  }
}
