package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import java.util.List;

public class FileCommand {
  @Parameter(names = "-file", converter = FileConverter.class)
  File file;

  @Parameter(names = "-files", converter = FileConverter.class)
  List<File> files;

  public static void main(String... argv) {
    FileCommand fc = new FileCommand();
    JCommander.newBuilder().addObject(fc).build().parse(argv);
    fc.run();
  }

  public void run() {
    System.out.println(file);
    for (File f: files)
      System.out.printf("%s %n", f);
  }
}
