package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

@SuppressWarnings("checkstyle:onetoplevelclass")
@Parameters(separators = "=",
            commandDescription = "Record changes to the repository")
class CommandCommit {

  @Parameter(description = "The list of files to commit")
  List<String> files;

  @Parameter(names = "--amend", description = "Amend")
  Boolean amend = false;

  @Parameter(names = "--author")
  String author;
}

@SuppressWarnings("checkstyle:onetoplevelclass")
@Parameters(commandDescription = "Add file contents to the index")
class CommandAdd {

  @Parameter(description = "File patterns to add to the index")
  List<String> patterns;

  @Parameter(names = "-i")
  Boolean interactive = false;
}

@Parameters(commandDescription = "Tool command")
public class TooledCommand {

  @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
  Boolean verbose;

  @Parameter(names = "-test", description = "Test")
  Integer test = 1;

  public static void main(String... argv) {
    TooledCommand tc = new TooledCommand();
    CommandAdd add = new CommandAdd();
    CommandCommit commit = new CommandCommit();
    JCommander jc = JCommander.newBuilder()
                        .addObject(tc)
                        .addCommand("add", add)
                        .addCommand("commit", commit)
                        .build();

    jc.parse(argv);

    Assert.assertTrue(tc.verbose);
    Assert.assertEquals(jc.getParsedCommand(), "commit");
    Assert.assertTrue(commit.amend);
    Assert.assertEquals(commit.author, "cbeust");
    Assert.assertEquals(commit.files, Arrays.asList("A.java", "B.java"));
    System.out.println("Asserts cleared");
  }
}
