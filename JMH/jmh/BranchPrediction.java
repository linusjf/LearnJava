package jmh;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("all")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class BranchPrediction {

  /*
   * This sample serves as a warning against regular data sets.
   *
   * It is very tempting to present a regular data set to benchmark, either due to
   * naive generation strategy, or just from feeling better about regular data sets.
   * Unfortunately, it frequently backfires: the regular datasets are known to be
   * optimized well by software and hardware. This example exploits one of these
   * optimizations: branch prediction.
   *
   * Imagine our benchmark selects the branch based on the array contents, as
   * we are streaming through it:
   */

  private static final int COUNT = 1024 * 1024;

  private byte[] sorted;
  private byte[] unsorted;

  @Setup
  public void setup() {
    sorted = new byte[COUNT];
    unsorted = new byte[COUNT];
    Random random = new Random(1234);
    random.nextBytes(sorted);
    random.nextBytes(unsorted);
    Arrays.sort(sorted);
  }

  @Benchmark
  @OperationsPerInvocation(COUNT)
  public void sorted(Blackhole bh1, Blackhole bh2) {
    for (byte v: sorted) {
      if (v > 0) {
        bh1.consume(v);
      } else {
        bh2.consume(v);
      }
    }
  }

  @Benchmark
  @OperationsPerInvocation(COUNT)
  public void unsorted(Blackhole bh1, Blackhole bh2) {
    for (byte v: unsorted) {
      if (v > 0) {
        bh1.consume(v);
      } else {
        bh2.consume(v);
      }
    }
  }

  /*
     There is a substantial difference in performance for these benchmarks!

     It is explained by good branch prediction in "sorted" case, and branch mispredicts in "unsorted"
     case. -prof perfnorm conveniently highlights that, with larger "branch-misses", and larger "CPI"
     for "unsorted" case:

     Benchmark                                                       Mode  Cnt   Score    Error  Units
     BranchPrediction.sorted                            avgt   25   2.160 ±  0.049  ns/op
     BranchPrediction.sorted:·CPI                       avgt    5   0.286 ±  0.025   #/op
     BranchPrediction.sorted:·branch-misses             avgt    5  ≈ 10⁻⁴            #/op
     BranchPrediction.sorted:·branches                  avgt    5   7.606 ±  1.742   #/op
     BranchPrediction.sorted:·cycles                    avgt    5   8.998 ±  1.081   #/op
     BranchPrediction.sorted:·instructions              avgt    5  31.442 ±  4.899   #/op

     BranchPrediction.unsorted                          avgt   25   5.943 ±  0.018  ns/op
     BranchPrediction.unsorted:·CPI                     avgt    5   0.775 ±  0.052   #/op
     BranchPrediction.unsorted:·branch-misses           avgt    5   0.529 ±  0.026   #/op  <--- OOPS
     BranchPrediction.unsorted:·branches                avgt    5   7.841 ±  0.046   #/op
     BranchPrediction.unsorted:·cycles                  avgt    5  24.793 ±  0.434   #/op
     BranchPrediction.unsorted:·instructions            avgt    5  31.994 ±  2.342   #/op

     It is an open question if you want to measure only one of these tests. In many cases, you have to measure
     both to get the proper best-case and worst-case estimate!
  */

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_36
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */
  public static void main(String[] args) throws RunnerException {
    Options opt =
        new OptionsBuilder()
            .include(".*" + BranchPrediction.class.getSimpleName() + ".*")
            .build();

    new Runner(opt).run();
  }
}
