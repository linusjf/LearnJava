package collections;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public enum ConcurrentSkipListMapDemo {
  ;

  public static void main(String[] args) {
    ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
    Thread[] threads = new Thread[25];
    int counter = 0;
    for (char i = 'A'; i < 'Z'; i++) {
      Task task = new Task(map, String.valueOf(i));
      threads[counter] = new Thread(task);
      threads[counter].start();
      counter++;
    }
    for (int i = 0; i < 25; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main: Size of the map: %d\n", map.size());

    Map.Entry<String, Contact> element;
    Contact contact;

    element = map.firstEntry();
    contact = element.getValue();
    System.out.printf("Main: First Entry: %s: %s\n", contact.getName(), contact.getPhone());

    element = map.lastEntry();
    contact = element.getValue();
    System.out.printf("Main: Last Entry: %s: %s\n", contact.getName(), contact.getPhone());
    System.out.printf("Main: Submap from A1996 to B1002: \n");
    ConcurrentNavigableMap<String, Contact> submap = map.subMap("A1996", "B1002");
    do {
      element = submap.pollFirstEntry();
      if (element != null) {
        contact = element.getValue();
        System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
      }
    } while (element != null);
  }

  static class Contact {
    private String name;
    private String phone;

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
    private ConcurrentSkipListMap<String, Contact> map;
    private String id;

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
