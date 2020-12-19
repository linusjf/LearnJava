package pmdtests;

public final class IfElse {

  private IfElse() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... argv) {
    int argc = argv.length;
    int hpos = 0;
    for (int i = 0; i < argc; i++) {
      if (argv[i] == "left")
        hpos++;
      else if (argv[i] == "right")
        hpos--;
      else if (argv[i] == "up")
        ;
      else if (argv[i] == "down")
        ;
      else
        System.err.printf("Unknown option %s%n", argv[i]);
    }
  }

  public static void nextMain(String... argv) {
    int argc = argv.length;
    int hpos = 0;
    int vpos = 0;
    for (int i = 0; i < argc; i++) {
      if (argv[i] == "left")
        hpos++;
      else if (argv[i] == "right")
        hpos--;
      else if (argv[i] == "up")
        vpos++;
      else if (argv[i] == "down")
        vpos--;
      else
        System.err.printf("Unknown option %s%n", argv[i]);
    }
  }
}
