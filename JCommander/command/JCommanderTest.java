package command;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.defaultprovider.PropertyFileDefaultProvider;
import com.beust.jcommander.internal.Lists;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;

@SuppressWarnings("checkstyle:magicnumber")
public class JCommanderTest {
  // clang-format off
  @Parameter List<String> parameters = Lists.newArrayList();

  // clang-format on
  @Parameter(
      names = {"-log", "-verbose"},
      description = "Level of verbosity")
  Integer verbose = 1;

  @Parameter(names = "-test", description = "Test")
  Integer test = 1;

  @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
  String groups;

  @Parameter(names = "-debug", description = "Debug mode", hidden = true)
  boolean debug;

  @DynamicParameter(names = "-D", description = "Dynamic parameters go here")
  Map<String, String> dynamicParams = new HashMap<>();

  @Parameter(names = "--help", help = true)
  boolean help;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... argv) {
    JCommanderTest jct = new JCommanderTest();
    JCommander jc =
        JCommander.newBuilder()
            .defaultProvider(new PropertyFileDefaultProvider())
            .addObject(jct)
            .build();
    jc.parse(argv);

    if (jct.help) jc.usage();
    Assert.assertEquals(2, jct.verbose.intValue());
    Assert.assertEquals(4, jct.test.intValue());
    Assert.assertEquals("unit1,unit2,unit3", jct.groups);
    Assert.assertEquals(true, jct.debug);
    Assert.assertEquals("value", jct.dynamicParams.get("option"));
    Assert.assertEquals(Arrays.asList("a", "b", "c"), jct.parameters);

    System.out.println("All asserts passed.");
  }
}
