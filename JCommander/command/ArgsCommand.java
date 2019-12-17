package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.junit.Assert;

@SuppressWarnings("checkstyle:onetoplevelclass")
class ArgsMaster {
  @Parameter(names = "-master")
  String master;
}

@SuppressWarnings("checkstyle:onetoplevelclass")
class ArgsSlave {
  @Parameter(names = "-slave")
  String slave;
}

public enum ArgsCommand {
  ;

  public static void main(String... args) {
    ArgsMaster m = new ArgsMaster();
    ArgsSlave s = new ArgsSlave();
    JCommander.newBuilder()
        .addObject(new Object[] {m, s})
        .build()
        .parse(args);

    Assert.assertEquals(m.master, "master");
    Assert.assertEquals(s.slave, "slave");
    System.out.println("Asserts cleared.");
  }
}
