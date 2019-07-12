package visitorpattern;

import java.util.ArrayList;
import java.util.List;

public enum TaxiBilling {
  ;

  public static void main(String[] args) {

    List<Transportable> list = new ArrayList<>();

    list.add(new Person(12.4f, 1));
    list.add(new Animal(24.5f, 1));
    list.add(new Luggage(10.9f, 5));

    Taxi taxi = new Taxi();
    for (Transportable t: list) {
      t.accept(taxi);
    }

    System.out.println(
        String.format("Total Fare : $%.2f", taxi.getTotalFare()));
  }
}
