package builder;

import static builder.NyPizza.Size.*;
import static builder.Pizza.Topping.*;

/**
 * Describe class <code>Main</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum Main {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("checkstyle:magicnumber")
  public static void main(String[] args) {
    NutritionFacts cocaCola =
        new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();

    System.out.println("Serving Coke: ");
    System.out.println(cocaCola);
    NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();

    System.out.println("Serving Pizza 1: ");
    System.out.println(pizza);
    Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
    System.out.println("Serving Pizza 2: ");
    System.out.println(calzone);
  }
}
