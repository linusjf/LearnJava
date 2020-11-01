package jmh;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("PMD.CommentSize")
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class DeadCode {

  /*
   * The downfall of many benchmarks is Dead-Code Elimination (DCE): compilers
   * are smart enough to deduce some computations are redundant and eliminate
   * them completely. If the eliminated part was our benchmarked code, we are
   * in trouble.
   *
   * Fortunately, JMH provides the essential infrastructure to fight this
   * where appropriate: returning the result of the computation will ask JMH
   * to deal with the result to limit dead-code elimination (returned results
   * are implicitly consumed by Blackholes, see JMHSample_09_Blackholes).
   */
  @SuppressWarnings("PMD.ImmutableField")
  private double x = Math.PI;

  @Benchmark
  public void baseline() {
    // do nothing, this is a baseline
  }

  @Benchmark
  public void measureWrong() {
    // This is wrong: result is not used and the entire computation is optimized away.
    Math.log(x);
  }

  @Benchmark
  public double measureRight() {
    // This is correct: the result is being used.
    return Math.log(x);
  }

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can see the unrealistically fast calculation in with measureWrong(),
   * while realistic measurement with measureRight().
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_08 -f 1
   *    (we requested single fork; there are also other options, see -h)
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(DeadCode.class.getSimpleName())
                      .forks(1)
                      .build();

    new Runner(opt).run();
  }
}
