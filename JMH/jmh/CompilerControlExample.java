package jmh;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("all")
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CompilerControlExample {

  /*
   * We can use HotSpot-specific functionality to tell the compiler what
   * do we want to do with particular methods. To demonstrate the effects,
   * we end up with 3 methods in this sample.
   */

  /**
   * These are our targets: - first method is prohibited from inlining - second method is forced to
   * inline - third method is prohibited from compiling
   *
   * <p>We might even place the annotations directly to the benchmarked methods, but this expresses
   * the intent more clearly.
   */
  public void targetBlank() {
    // this method was intentionally left blank
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  public void targetDontInline() {
    // this method was intentionally left blank
  }

  @CompilerControl(CompilerControl.Mode.INLINE)
  public void targetInline() {
    // this method was intentionally left blank
  }

  @CompilerControl(CompilerControl.Mode.EXCLUDE)
  public void targetExclude() {
    // this method was intentionally left blank
  }

  /*
   * These method measures the calls performance.
   */

  @Benchmark
  public void baseline() {
    // this method was intentionally left blank
  }

  @Benchmark
  public void blank() {
    targetBlank();
  }

  @Benchmark
  public void dontinline() {
    targetDontInline();
  }

  @Benchmark
  public void inline() {
    targetInline();
  }

  @Benchmark
  public void exclude() {
    targetExclude();
  }

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * Note the performance of the baseline, blank, and inline methods are the same.
   * dontinline differs a bit, because we are making the proper call.
   * exclude is severely slower, becase we are not compiling it at all.
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_16 -wi 0 -i 3 -f 1
   *    (We requested no warmup iterations,3 iterations, single fork.
   *    There are also other options, see -h.)
   *
   * b) Via the Java API:
   *    (See the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(CompilerControlExample.class.getSimpleName())
                      .warmupIterations(0)
                      .measurementIterations(3)
                      .forks(1)
                      .build();
    new Runner(opt).run();
  }
}
