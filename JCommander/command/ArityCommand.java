package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.util.List;

public class ArityCommand {
  @Parameter(names = "-pairs", arity = 2, description = "Pairs") private List<String> pairs;

  public static void main(String... argv) {
    ArityCommand ac = new ArityCommand();

    JCommander.newBuilder().addObject(ac).build().parse(argv);
    ac.run();
  }

  public void run() {
    for (String one : pairs) System.out.println(one);
  }
}
