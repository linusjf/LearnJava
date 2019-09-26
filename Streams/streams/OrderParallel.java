package streams;

import java.util.ArrayList;
import java.util.List;

public enum OrderParallel {
  ;

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("C");
    list.add("H");
    list.add("A");
    list.add("A");
    list.add("B");
    list.add("F");
    list.add("");

    System.out.println("Ordered...");
    list.parallelStream()           
        .filter(s -> !s.isEmpty())  
        .distinct()               
        .sorted()                   
        .forEachOrdered(s -> System.out.println(s));
    System.out.println("Unordered...");
    list.parallelStream()           
        .filter(s -> !s.isEmpty())  
        .distinct()                 
        .sorted()                   
        .forEach(s -> System.out.println(s)); 
  }
}
