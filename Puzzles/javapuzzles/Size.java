package javapuzzles;

import java.util.Set;
import java.util.HashSet;

public enum Size {
  ;

  public static void main(String... args) {
    Set<Student> students = new HashSet<>();
    students.add(new Student(1));
    students.add(new Student(2));
    students.add(new Student(5));
    students.add(new Student(1));
    students.add(new Student(5));
    System.out.println(students.size());
  }
  
  static class Student {
    int id;

    Student(int id) {
      this.id = id;
    }
  }
}
