package jls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public final class HeapPollutionExample {

  private HeapPollutionExample() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String[] ignored) {

    List<String> stringListA = new ArrayList<>();
    List<String> stringListB = new ArrayList<>();

    ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
    ArrayBuilder.addToList(stringListA, "Ten", "Eleven", "Twelve");
    List<List<String>> listOfStringLists = new ArrayList<>();
    ArrayBuilder.addToList(listOfStringLists, stringListA, stringListB);

    ArrayBuilder.addToList2(listOfStringLists, stringListA, stringListB);
    ArrayBuilder.addToList3(listOfStringLists, stringListA, stringListB);

    ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
  }
}
