package streams;

import java.util.List;
import java.util.stream.Stream;

class Person {
  private final String name;
  private final int age;

  private final String gender;
  private final List<Person> siblings;

  public Person(String name, int age, String gender, List<Person> siblings) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.siblings = siblings;
  }

  public String getGender() {
    return gender;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public List<Person> getSiblings() {
    return siblings;
  }

  public boolean hasSiblings() {
    return !siblings.isEmpty();
  }

  public Stream<Person> asStream() {
    return Stream.of(this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    sb.append("{name: ")
        .append(name)
        .append(",age: ")
        .append(age)
        .append(",gender: ")
        .append(gender)
        .append(",siblings: ")
        .append(siblings.size())
        .append('}');
    return sb.toString();
  }
}
