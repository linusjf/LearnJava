package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParametersDelegate;
import org.junit.Assert;

@SuppressWarnings("checkstyle:onetoplevelclass")
class Delegate {
  @Parameter(names = "-port") 
  int port;
}

public class DelegateCommand {
  @Parameter(names = "-v") 
  boolean verbose;

  @ParametersDelegate Delegate delegate = new Delegate();

  public static void main(String... argv) {
    DelegateCommand p = new DelegateCommand();
    JCommander.newBuilder().addObject(p).build().parse(argv);
    Assert.assertTrue(p.verbose);
    Assert.assertEquals(p.delegate.port, 1234);
    System.out.println("Asserts cleared.");
  }
}
