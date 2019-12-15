package sbtests;

@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public class Twisted {
  private final String name;

  Twisted(String name) {
    this.name = name;
  }

  private String name() {
    return name;
  }

  String getName() {
    return name;
  }

  private void reproduce() {
    new Twisted("reproduce") {

      void printName() {
        System.out.println(name());
      }
    }
      .printName();
  }

  private void reiterate() {
    new Twisted("reiterate") {

      void printName() {
        System.out.println(getName());
      }
    }
      .printName();
  }

  public static void main(String[] args) {
    new Twisted("main").reproduce();
    new Twisted("main").reiterate();
  }
}
