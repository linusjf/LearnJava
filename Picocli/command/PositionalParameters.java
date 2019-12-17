package command;

import java.io.File;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;

public class PositionalParameters {
  // "hidden": don't show this parameter in usage help message
  @Parameters(hidden = true)
  List<String> allParameters;

  // no "index" attribute: captures _all_ arguments (as Strings)
  @Parameters(index = "0")
  InetAddress host;

  @Parameters(index = "1")
  int port;

  @Parameters(index = "2..*")
  File[] files;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    PositionalParameters params =
        CommandLine.populateCommand(
            new PositionalParameters(), args);
    assert params.host.getHostName().equals("localhost");
    assert params.port == 12_345;
    assert Arrays.equals(
        params.files,
        new File[] {new File("file1.txt"),
                    new File("file2.txt")});
    assert params.allParameters.equals(Arrays.asList(
        "localhost", "12345", "file1.txt", "file2.txt"));
    System.out.println("Cleared all asserts");
  }
}
