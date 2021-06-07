package streams;

import java.util.List;

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
}
