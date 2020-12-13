package pmdtests;

public class IfElse {
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
}
