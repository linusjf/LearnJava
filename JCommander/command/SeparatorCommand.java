package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@SuppressWarnings("PMD.ImmutableField")
@Parameters(separators = "=")
public class SeparatorCommand {
  @Parameter(names = "-level")
  private Integer level = 2;

  public static void main(String... argv) {
    SeparatorCommand fc = new SeparatorCommand();
    JCommander.newBuilder().addObject(fc).build().parse(argv);
    fc.run();
  }

  public void run() {
    System.out.printf("%d %n", level);
  }
}
