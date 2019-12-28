package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.io.File;
import java.util.List;

public class FileListCommand {
  @Parameter(names = "-files", listConverter = FileListConverter.class) 
  List<File> files;

  public static void main(String... argv) {
    FileListCommand fc = new FileListCommand();
    JCommander.newBuilder().addObject(fc).build().parse(argv);
    fc.run();
  }

  public void run() {
    for (File f : files) 
      System.out.printf("%s %n", f);
  }
}
