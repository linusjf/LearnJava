package collections;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

@SuppressWarnings("PMD.SystemPrintln")
public enum ConcurrentSkipListMapDemo {
  ;

  public static void main(String[] args) {
    Thread[] threads = new Thread[25];
    ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
    startThreads(threads, map);
    for (Thread t: threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main: Size of the map: %d%n", map.size());
    printFirstEntry(map);
    printLastEntry(map);
    System.out.printf("Main: Submap from A1996 to B1002: %n");
    ConcurrentNavigableMap<String, Contact> submap =
        map.subMap("A1996", "B1002");
    printSubMap(submap);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void printSubMap(
      ConcurrentNavigableMap<String, Contact> submap) {
    Map.Entry<String, Contact> element = submap.pollFirstEntry();
    while (element != null) {
      Contact contact = element.getValue();
      printContact(contact);
      element = submap.pollFirstEntry();
    }
  }

  private static void printContact(Contact contact) {
    System.out.printf("%s: %s%n", contact.getName(), contact.getPhone());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void printFirstEntry(
      ConcurrentSkipListMap<String, Contact> map) {
    Map.Entry<String, Contact> element = map.firstEntry();
    Contact contact = element.getValue();
    System.out.printf("Main: First Entry: ");
    printContact(contact);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void printLastEntry(
      ConcurrentSkipListMap<String, Contact> map) {
    Map.Entry<String, Contact> element = map.lastEntry();
    Contact contact = element.getValue();
    System.out.printf("Main: Last Entry: ");
    printContact(contact);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void startThreads(Thread[] threads,
                                   ConcurrentSkipListMap<String, Contact> map) {
    for (char i = 'A'; i < 'Z'; i++) {
      Task task = new Task(map, String.valueOf(i));
      int index = i - 'A';
      threads[index] = new Thread(task);
      threads[index].start();
    }
  }

  static class Contact {
    private final String name;
    private final String phone;

    Contact(String name, String phone) {
      this.name = name;
      this.phone = phone;
    }

    public String getName() {
      return name;
    }

    public String getPhone() {
      return phone;
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Runnable {
    private final ConcurrentSkipListMap<String, Contact> map;
    private final String id;

    Task(ConcurrentSkipListMap<String, Contact> map, String id) {
      this.id = id;
      this.map = map;
    }

    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        Contact contact = new Contact(id, String.valueOf(i + 1000));
        map.put(id + contact.getPhone(), contact);
      }
    }
  }
}
