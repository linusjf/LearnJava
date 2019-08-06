package javapuzzles;

import java.util.Random;

public enum HelloGoodbye {
  ;
  public static void main(String[] args) {
    Random random = new Random();
    int which = random.nextInt(3);
    switch (which) {
      case 0:
        shutdownHooksMain(args);
        break;

      case 1:
        finallyMain(args);
        break;

      default:
        haltMain(args);
        break;
    }
  }

  public static void finallyMain(String... args) {
    try {
      System.out.println("Hello world");
      System.exit(0);
    } finally {
      System.out.println("Finally: Goodbye world");
    }
  }

  public static void shutdownHooksMain(String... args) {
    System.out.println("Hello world");
    Runtime.getRuntime().addShutdownHook(new Thread(() ->
        System.out.println("Shutdown Hook: Goodbye world")));
    System.exit(0);
  }

  public static void haltMain(String... args) {
    System.out.println("Halting...Hello world");
    Runtime.getRuntime().halt(1);
  }
}
