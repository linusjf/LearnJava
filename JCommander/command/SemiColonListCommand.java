package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import java.util.List;

public class SemiColonListCommand {
  @Parameter(
    names = "-files",
    converter = FileConverter.class,
    splitter = SemiColonSplitter.class
  )
  List<File> files;

  public static void main(String... argv) {
    SemiColonListCommand fc = new SemiColonListCommand();
    JCommander.newBuilder().addObject(fc).build().parse(argv);
    fc.run();
  }

  public void run() {
    for (File f : files) System.out.printf("%s %n", f);
  }
}
