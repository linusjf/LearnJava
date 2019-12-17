package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("PMD.ImmutableField")
public class MainCommand {
  @Parameter(description = "Files")
  private List<String> files = new ArrayList<>();

  @Parameter(names = "-debug",
             description = "Debugging level")
  private Integer debug = 1;

  public static void main(String... argv) {
    MainCommand fc = new MainCommand();
    JCommander.newBuilder().addObject(fc).build().parse(
        argv);
    fc.run();
  }

  public void run() {
    System.out.printf("%d %n", debug);
    for (String file: files)
      System.out.println(file);
  }
}
