package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("all")
@State(Scope.Thread)
public class FixtureLevel {

  double x;

  /*
   * Fixture methods have different levels to control when they should be run.
   * There are at least three Levels available to the user. These are, from
   * top to bottom:
   *
   * Level.Trial: before or after the entire benchmark run (the sequence of iterations)
   * Level.Iteration: before or after the benchmark iteration (the sequence of invocations)
   * Level.Invocation; before or after the benchmark method invocation.
   * (WARNING: read the Javadoc before using)
   *
   * Time spent in fixture methods does not count into the performance
   * metrics, so you can use this to do some heavy-lifting.
   */

  @TearDown(Level.Iteration)
  public void check() {
    assert x > Math.PI : "Nothing changed?";
  }

  @Benchmark
  public void measureRight() {
    x++;
  }

  @SuppressWarnings({"PMD.UnusedLocalVariable", "PMD.UnusedAssignment"})
  @Benchmark
  public void measureWrong() {
    double x = 0;
    x++;
  }

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can see measureRight() yields the result, and measureWrong() fires
   * the assert at the end of first iteration! This will not generate the results
   * for measureWrong(). You can also prevent JMH for proceeding further by
   * requiring "fail on error".
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -ea -jar target/benchmarks.jar JMHSample_06 -f 1
   *    (we requested single fork; there are also other options, see -h)
   *
   *    You can optionally supply -foe to fail the complete run.
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(FixtureLevel.class.getSimpleName())
                      .forks(1)
                      .jvmArgs("-ea")
                      .shouldFailOnError(false)
                      .build();

    // switch to "true" to fail the complete run
    new Runner(opt).run();
  }
}
