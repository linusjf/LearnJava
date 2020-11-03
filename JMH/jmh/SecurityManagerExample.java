package jmh;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.Policy;
import java.security.URIParameter;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("all")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SecurityManagerExample {

  /*
   * Some targeted tests may care about SecurityManager being installed.
   * Since JMH itself needs to do privileged actions, it is not enough
   * to blindly install the SecurityManager, as JMH infrastructure will fail.
   */

  /*
   * In this example, we want to measure the performance of System.getProperty
   * with SecurityManager installed or not. To do this, we have two state classes
   * with helper methods. One that reads the default JMH security policy (we ship one
   * with JMH), and installs the security manager; another one that makes sure
   * the SecurityManager is not installed.
   *
   * If you need a restricted security policy for the tests, you are advised to
   * get /jmh-security-minimal.policy, that contains the minimal permissions
   * required for JMH benchmark to run, merge the new permissions there, produce new
   * policy file in a temporary location, and load that policy file instead.
   * There is also /jmh-security-minimal-runner.policy, that contains the minimal
   * permissions for the JMH harness to run, if you want to use JVM args to arm
   * the SecurityManager.
   */

  @State(Scope.Benchmark)
  public static class SecurityManagerInstalled {
    @Setup
    public void setup()
        throws IOException, NoSuchAlgorithmException, URISyntaxException {
      URI policyFile =
          SecurityManager.class.getResource("jmh-security.policy").toURI();
      Policy.setPolicy(
          Policy.getInstance("JavaPolicy", new URIParameter(policyFile)));
      System.setSecurityManager(new SecurityManager());
    }

    @TearDown
    public void teardown() {
      System.setSecurityManager(null);
    }
  }

  @State(Scope.Benchmark)
  public static class SecurityManagerEmpty {
    @Setup
    public void setup()
        throws IOException, NoSuchAlgorithmException, URISyntaxException {
      System.setSecurityManager(null);
    }
  }

  @Benchmark
  public String testWithSM(SecurityManagerInstalled s)
      throws InterruptedException {
    return System.getProperty("java.home");
  }

  @Benchmark
  public String testWithoutSM(SecurityManagerEmpty s)
      throws InterruptedException {
    return System.getProperty("java.home");
  }

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_33 -f 1
   *    (we requested 5 warmup iterations, 5 forks; there are also other options, see -h))
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(SecurityManagerExample.class.getSimpleName())
                      .warmupIterations(5)
                      .measurementIterations(5)
                      .forks(1)
                      .build();
    new Runner(opt).run();
  }
}
