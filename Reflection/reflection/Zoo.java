package reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"PMD.ShortClassName", "PMD.DataClass"})
public class Zoo {
  String city;
  String name;

  Animal[] animals;

  public Zoo(String city, String name) {
    this.city = city;
    this.name = name;
    this.animals = new Animal[0];
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Animal[] getAnimals() {
    return animals.clone();
  }

  public void setAnimals(List<Animal> animals) {
    this.animals = animals.toArray(new Animal[0]);
  }

  public void add(Animal al) {
    List<Animal> beasts = new ArrayList<>(Arrays.asList(animals));
    beasts.add(al);
    this.animals = beasts.toArray(new Animal[0]);
  }
}
