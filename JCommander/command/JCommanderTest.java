package command;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.internal.Lists;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;

public class JCommanderTest {
  @Parameter
  public List<String> parameters = Lists.newArrayList();

  @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
  public Integer verbose = 1;

  @Parameter(names = "-groups",
             description = "Comma-separated list of group names to be run")
  public String groups;

  @Parameter(names = "-debug", description = "Debug mode")
  public boolean debug;

  @DynamicParameter(names = "-D", description = "Dynamic parameters go here")
  public Map<String, String> dynamicParams = new HashMap<>();

  public static void main(String... argv) {
    JCommanderTest jct = new JCommanderTest();
    JCommander.newBuilder().addObject(jct).build().parse(argv);

    Assert.assertEquals(2, jct.verbose.intValue());
    Assert.assertEquals("unit1,unit2,unit3", jct.groups);
    Assert.assertEquals(true, jct.debug);
    Assert.assertEquals("value", jct.dynamicParams.get("option"));
    Assert.assertEquals(Arrays.asList("a", "b", "c"), jct.parameters);

    System.out.println("All asserts passed.");
  }
}
