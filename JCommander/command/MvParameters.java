package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.SubParameter;
import org.junit.Assert;

public class MvParameters {
  @SubParameter(order = 0)
  public String from;

  @SubParameter(order = 1)
  public String to;

  public static void main(String... argv) {
    class Parameters {
      @Parameter(
          names = {"-mv"},
          arity = 2)
      public MvParameters mvParameters;
    }

    Parameters args = new Parameters();
    JCommander.newBuilder().addObject(args).args(argv).build();

    Assert.assertNotNull(args.mvParameters);
    Assert.assertEquals(args.mvParameters.from, "from");
    Assert.assertEquals(args.mvParameters.to, "to");
    System.out.println("Assert cleared.");
  }
}
