package javapuzzles;

public enum Client {
  ;

  public static void main(String[] args) {
    System.out.println(Cache.getSum());
  }
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD"})
enum Cache {
  ;
  private static int sum;
  private static boolean initialized = false;

  static {
    initializeIfNecessary();
  }

  public static int getSum() {
    initializeIfNecessary();
    return sum;
  }

  private static synchronized void initializeIfNecessary() {
    if (!initialized) {
      for (int i = 0; i < 100; i++)
        sum += i;
      initialized = true;
    }
  }
}
