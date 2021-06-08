package streams;

import java.util.List;
import java.util.stream.Stream;

class Person {
  private String name;
  private int age;

  private String gender;
  private List<Person> siblings;

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
    return siblings.size() > 0;
  }

  public Stream<Person> asStream() {
    return Stream.of(this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    sb.append('{')
        .append("name: ")
        .append(name)
        .append(',')
        .append("age: ")
        .append(age)
        .append(',')
        .append("gender: ")
        .append(gender)
        .append(',')
        .append("siblings: ")
        .append(siblings.size())
        .append('}');
    return sb.toString();
  }
}
