package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

public class VariableArityCommand {

  @Parameter(names = "-foo", variableArity = true)
  public List<String> foo = new ArrayList<>();

  public static void main(String... argv) {
    VariableArityCommand ac = new VariableArityCommand();

    JCommander.newBuilder().addObject(ac).build().parse(argv);
    ac.run();
  }

  public void run() {
    for (String one : foo) System.out.println(one);
  }
}
