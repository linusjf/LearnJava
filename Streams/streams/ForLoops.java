package streams;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ForLoops {
  private static Faker faker = new Faker();
  private static Random random = new Random();
  private static final int MAX_SIBLINGS = 10;
  private static final int PERSON_COUNT = 10_000;

  private ForLoops() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    List<Person> persons = new ArrayList<>();
    for (int i = 0; i < PERSON_COUNT; i++) {
      Person[] siblings = getNewSiblings();
      for (int j = 0; j < siblings.length; j++)
        persons.add(siblings[j]);
    }
  }

  private static Person[] getNewSiblings() {
    int count = random.nextInt(MAX_SIBLINGS) + 1;
    Person[] persons = new Person[count];
    for (int i = 0; i < count; i++) {
      String name = faker.name().fullName();
      int ageMarker = faker.number().numberBetween(0, 150);
      int min = (ageMarker - 10 >= 0) ? ageMarker - 10 : 0;
      int max = (ageMarker + 10 >= 150) ? 150 : ageMarker + 10;
      int age = faker.number().numberBetween(min, max);
      String gender = faker.bool().bool() ? "M" : "F";
      Person person = new Person(name, age, gender, new ArrayList<>(count - 1));
      persons[i] = person;
    }
    for (int i = 0; i < count; i++) {
      Person person = persons[i];
      List<Person> siblings = person.getSiblings();
      for (int j = 0; j < count; j++) {
        if (i != j)
          siblings.add(persons[j]);
      }
    }
    return persons;
  }
}
