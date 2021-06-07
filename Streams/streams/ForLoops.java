package streams;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    streaming(persons);
    streamingNames(persons);
    streamingMales(persons);
  }

  private static void streamingMales(List<Person> persons) {
    List<String> result = persons.stream()
                              .filter(p -> p.getGender().equals("M"))
                              .filter(p -> p.getAge() > 18)
                              .filter(p -> p.getAge() <= 65)
                              .filter(p -> p.getName() != null)
                              .filter(p -> p.getName().startsWith("B"))
                              .map(p -> p.getName())
                              .collect(Collectors.toList());
    System.out.println("Male Names starting with B: " + result.size());
  }

  private static void streamingNames(List<Person> persons) {
    List<String> result = persons.stream()
                              .filter(p -> p.getAge() > 18)
                              .filter(p -> p.getAge() <= 65)
                              .filter(p -> p.getName() != null)
                              .filter(p -> p.getName().startsWith("B"))
                              .map(p -> p.getName())
                              .collect(Collectors.toList());
    System.out.println("Names starting with B: " + result.size());
  }

  private static void streaming(List<Person> persons) {
    List<String> result = persons.stream()
                              .filter(p -> p.getAge() > 18)
                              .map(p -> p.getName())
                              .collect(Collectors.toList());
    System.out.println("Age greater than 18: " + result.size());
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
