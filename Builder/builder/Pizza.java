package builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

// Builder pattern for class hierarchies
/**
 * Describe class <code>Pizza</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public abstract class Pizza {

  public enum Topping {
    HAM,
    MUSHROOM,
    ONION,
    PEPPER,
    SAUSAGE;
  }

  final Set<Topping> toppings;

  abstract static class Builder<T extends Builder<T>> {
    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

    public T addTopping(Topping topping) {
      toppings.add(Objects.requireNonNull(topping));
      return self();
    }

    abstract Pizza build();

    // Subclasses must override this method to return "this"
    protected abstract T self();
  }

  /** Creates a new <code>Pizza</code> instance. */
  Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone();
    // See Item 50
  }
}
