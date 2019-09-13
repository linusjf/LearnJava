package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum FlattenStreams {
  ;

  public static <T> List<T> flattenStream(Collection<List<T>> lists) {
    List<T> finalList = new ArrayList<T>();

    for (List<T> list: lists)
      list.stream().forEach(finalList::add);

    return finalList;
  }

  public static <T> List<T> flattenParallelStream(Collection<List<T>> lists) {
    List<T> finalList = new ArrayList<T>();

    for (List<T> list: lists)
      list.parallelStream().forEach(finalList::add);

    return finalList;
  }

  public static <T> List<T> flattenParallelStreamSynchronized(
      Collection<List<T>> lists) {
    List<T> finalList = new ArrayList<T>();
    finalList = Collections.synchronizedList(finalList);

    for (List<T> list: lists)
      list.parallelStream().forEach(finalList::add);

    return finalList;
  }

  public static void main(String... args) {
    Map<Integer, List<Character>> map = new HashMap<>();
    map.put(1, Arrays.asList('G', 'e', 'e', 'k', 's'));
    map.put(2, Arrays.asList('F', 'o', 'r'));
    map.put(3, Arrays.asList('G', 'e', 'e', 'k', 's'));
    List<Character> flatList = flattenStream(map.values());
    System.out.println(flatList);
    flatList = flattenParallelStreamHeinz(map.values());
    System.out.println(flatList);
    flatList = flattenParallelStream(map.values());
    System.out.println(flatList);
    flatList = flattenParallelStreamSynchronized(map.values());
    System.out.println(flatList);
  }

  public static <T> List<T> flattenParallelStreamHeinz(
      Collection<List<T>> lists) {
    return lists.parallelStream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }
}
