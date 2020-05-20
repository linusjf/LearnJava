package command;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class ClusteredShortOptions {
  @Option(names = "-a")
  boolean aaa;

  @Option(names = "-b")
  boolean bbb;

  @Option(names = "-c")
  boolean ccc;

  @Option(names = "-f")
  String file;

  public static void main(String... args) {
    ClusteredShortOptions cso = new ClusteredShortOptions();

    CommandLine cl = new CommandLine(cso);
    cl.parseArgs("-abcfInputFile.txt");
    cl.parseArgs("-abcf=InputFile.txt");
    cl.parseArgs("-abc", "-f=InputFile.txt");
    cl.parseArgs("-ab", "-cf=InputFile.txt");
    cl.parseArgs("-a", "-b", "-c", "-fInputFile.txt");
    cl.parseArgs("-a", "-b", "-c", "-f", "InputFile.txt");
    cl.parseArgs("-a", "-b", "-c", "-f=InputFile.txt");

    System.out.println("Arguments parsed.");
  }
}
