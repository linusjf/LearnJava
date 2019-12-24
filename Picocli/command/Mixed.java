package command;

import java.util.Arrays;
import java.util.List;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class Mixed {
  @Parameters List<String> positional;

  @Option(names = "-o")
  List<String> options;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    Mixed mixed = new Mixed();
    new CommandLine(mixed).parseArgs(args);

    assert mixed.positional.equals(Arrays.asList("param0", "param1", "param2", "param3"));
    assert mixed.options.equals(Arrays.asList("AAA", "BBB"));
    System.out.println("asserts cleared");
  }
}
