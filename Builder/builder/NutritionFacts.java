package builder;

// Builder Pattern
/**
 * Describe class <code>NutritionFacts</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  private NutritionFacts(Builder bilder) {
    servingSize = bilder.servingSize;
    servings = bilder.servings;
    calories = bilder.calories;
    fat = bilder.fat;
    sodium = bilder.sodium;
    carbohydrate = bilder.carbohydrate;
  }

  /**
   * Describe <code>toString</code> method here.
   *
   * @return a <code>String</code> value
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    sb.append("serving size = ")
        .append(servingSize)
        .append(", servings = ")
        .append(servings)
        .append(", calories = ")
        .append(calories)
        .append(", fat = ")
        .append(fat)
        .append(", sodium = ")
        .append(sodium)
        .append(", carbohydrates = ")
        .append(carbohydrate);
    return sb.toString();
  }

  /** Describe class <code>Builder</code> here. */
  public static class Builder {
    // Required parameters
    private final int servingSize;
    private final int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

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
}
