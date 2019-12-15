package command;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class BooleanOptions {
  @Option(
    names = "-x",
    defaultValue = "false",
    fallbackValue = "true",
    arity = "0..1",
    description = "Option with optional parameter. Default: ${DEFAULT-VALUE}, " +
      "if specified without parameter: ${FALLBACK-VALUE}"
  )
  boolean x;

  public static void main(String... args) {
    BooleanOptions bo = new BooleanOptions();
    CommandLine cl = new CommandLine(bo);
    cl.parseArgs();
    System.out.println(bo.x);

    cl.parseArgs("-x");
    System.out.println(bo.x);
    cl.parseArgs("-x", "true");
    System.out.println(bo.x);
    cl.parseArgs("-x");
    System.out.println(bo.x);
    cl.parseArgs();
    System.out.println(bo.x);

    // allow toggling
    cl.setToggleBooleanFlags(true);
    System.out.println("After allowing toggling...");

    cl.parseArgs();
    System.out.println(bo.x);

    cl.parseArgs("-x");
    System.out.println(bo.x);
    cl.parseArgs("-x", "true");
    System.out.println(bo.x);
    cl.parseArgs("-x");
    System.out.println(bo.x);
    cl.parseArgs();
    System.out.println(bo.x);
  }
}
