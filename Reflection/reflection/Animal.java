package reflection;

public class Animal {
  public String name, gender, classification;
  public int weight;

  public Animal(String name, String gender, String classification, int weight) {
    this.name = name;
    this.gender = gender;
    this.classification = classification;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
