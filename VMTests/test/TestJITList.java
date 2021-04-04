package test;

import java.util.ArrayList;
import java.util.List;

public final class TestJITList {

  private TestJITList() {
    throw new IllegalStateException("Private constructor");
  }

  private static void call(List<String> list) {
    list.add("foo");
  }

  public static void main(String[] args) throws InterruptedException {
    List<List<String>> lists = new ArrayList<>();
    lists.add(new ArrayList<String>());
    for (int i = 0; i < 10_000; i++)
      call(lists.get(i % lists.size()));
    Thread.sleep(1000);
  }
}
