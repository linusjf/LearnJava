package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;

public class ValidatedCommand {
  @Parameter(names = "-age",
             validateWith = PositiveInteger.class)
  private Integer age;

  public static void main(String... argv) {
    ValidatedCommand fc = new ValidatedCommand();
    JCommander.newBuilder().addObject(fc).build().parse(
        argv);
    fc.run();
  }

  public void run() {
    System.out.printf("%d %n", age);
  }
}
