package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ArgsPassword {
  @Parameter(names = "-password", 
  description = "Connection password", 
  password = true, 
  echoInput = true)
  private String password;

  public static void main(String... argv) {
    ArgsPassword argsPwd = new ArgsPassword();
    JCommander.newBuilder().addObject(argsPwd).build().parse(argv);
    argsPwd.run();
  }

  public void run() {
    System.out.printf("%s %n", password);
  }
}
