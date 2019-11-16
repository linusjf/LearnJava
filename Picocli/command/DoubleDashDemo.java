package command;

import java.util.Arrays;
import java.util.List;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class DoubleDashDemo {
  @Option(names = "-v")
  boolean verbose;

  @Option(names = "-files")
  List<String> files;

  @Parameters
  List<String> params;

  public static void main(String... argv) {
    String[] args = {"-v", "--", "-files", "file1", "file2"};
    DoubleDashDemo demo = new DoubleDashDemo();
    new CommandLine(demo).parseArgs(args);

    assert demo.verbose;
    assert demo.files == null;
    assert demo.params.equals(Arrays.asList("-files", "file1", "file2"));
    System.out.println("Asserts cleared");
  }
}
