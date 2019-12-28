package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("PMD.ShortClassName")
public class Main {
  @Parameter(names = {"--length", "-l"}) 
  int length;

  @Parameter(names = "-host", description = "The host") 
  List<String> hosts = new ArrayList<>();

  @Parameter(names = {"--pattern", "-p"}) 
  int pattern;

  public static void main(String... argv) {
    Main main = new Main();
    JCommander.newBuilder().addObject(main).build().parse(argv);
    main.run();
  }

  public void run() {
    System.out.printf("%d %d %n", length, pattern);
    for (String host : hosts) 
      System.out.println(host);
  }
}
