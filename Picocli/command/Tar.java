package command;

import java.io.File;
import java.util.Arrays;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParseResult;

@SuppressWarnings("PMD.ShortClassName")
public class Tar {
  @Option(names = "-c", description = "create a new archive")
  boolean create;

  @Option(names = {"-f", "--file"},
          paramLabel = "ARCHIVE",
          description = "the archive file")
  File archive;

  @Parameters(paramLabel = "FILE",
              description = "one ore more files to archive")
  File[] files;

  @Option(names = {"-h", "--help"},
          usageHelp = true,
          description = "display a help message")
  private boolean helpRequested;

  public static void main(String... args) {
    Tar tar = new Tar();

    final ParseResult parseResult = new CommandLine(tar).parseArgs(args);

    assert !tar.helpRequested;
    assert tar.create;
    assert tar.archive.equals(new File("result.tar"));
    assert Arrays.equals(
        tar.files, new File[] {new File("file1.txt"), new File("file2.txt")});
    System.out.println(parseResult);
  }
}
