package command;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "negatable-options-demo")
class NegatableOptionsDemo {
  @Option(names = "--verbose", negatable = true)
  boolean verbose;

  @Option(names = "-XX:+PrintGCDetails", negatable = true)
  boolean printGCDetails;

  @Option(names = "-XX:-UseG1GC", negatable = true)
  boolean useG1GC = true;

  @Option(names = "--no-backup",
          negatable = true,
          description = "Make a backup. True by default.")
  boolean backup = true;

  public static void main(String... args) {
    NegatableOptionsDemo nod = new NegatableOptionsDemo();
    CommandLine cl = new CommandLine(nod);
    cl.usage(System.err);
  }
}
