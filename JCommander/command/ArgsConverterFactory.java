package command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.junit.Assert;

@SuppressWarnings("checkstyle:magicnumber")
public class ArgsConverterFactory {
  @Parameter(names = "-hostport")
  private HostPort hostPort;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... argv) {
    ArgsConverterFactory a = new ArgsConverterFactory();
    JCommander jc =
        JCommander.newBuilder().addObject(a).addConverterFactory(new HostPortFactory()).build();
    jc.parse(argv);

    Assert.assertEquals(a.hostPort.host, "example.com");
    Assert.assertEquals(a.hostPort.port.intValue(), 8080);
    System.out.println("All asserts cleared.");
  }
}
