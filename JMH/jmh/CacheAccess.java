package jmh;

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
public class CacheAccess {

  /*
   * This sample serves as a warning against subtle differences in cache access patterns.
   *
   * Many performance differences may be explained by the way tests are accessing memory.
   * In the example below, we walk the matrix either row-first, or col-first:
   */

  private static final int COUNT = 4096;
  private static final int MATRIX_SIZE = COUNT * COUNT;

  private int[][] matrix;

  @Setup
  public void setup() {
    matrix = new int[COUNT][COUNT];
    Random random = new Random(1234);
    for (int i = 0; i < COUNT; i++) {
      for (int j = 0; j < COUNT; j++) {
        matrix[i][j] = random.nextInt();
      }
    }
  }

  @Benchmark
  @OperationsPerInvocation(MATRIX_SIZE)
  public void colFirst(Blackhole bh) {
    for (int c = 0; c < COUNT; c++) {
      for (int r = 0; r < COUNT; r++) {
        bh.consume(matrix[r][c]);
      }
    }
  }

  @Benchmark
  @OperationsPerInvocation(MATRIX_SIZE)
  public void rowFirst(Blackhole bh) {
    for (int r = 0; r < COUNT; r++) {
      for (int c = 0; c < COUNT; c++) {
        bh.consume(matrix[r][c]);
      }
    }
  }

  /*
     Notably, colFirst accesses are much slower, and that's not a surprise: Java's multidimensional
     arrays are actually rigged, being one-dimensional arrays of one-dimensional arrays. Therefore,
     pulling n-th element from each of the inner array induces more cache misses, when matrix is large.
     -prof perfnorm conveniently highlights that, with >2 cache misses per one benchmark op:

     Benchmark                                                 Mode  Cnt   Score    Error  Units
     MatrixCopy.colFirst                          avgt   25   5.306 ±  0.020  ns/op
     MatrixCopy.colFirst:·CPI                     avgt    5   0.621 ±  0.011   #/op
     MatrixCopy.colFirst:·L1-dcache-load-misses   avgt    5   2.177 ±  0.044   #/op <-- OOPS
     MatrixCopy.colFirst:·L1-dcache-loads         avgt    5  14.804 ±  0.261   #/op
     MatrixCopy.colFirst:·LLC-loads               avgt    5   2.165 ±  0.091   #/op
     MatrixCopy.colFirst:·cycles                  avgt    5  22.272 ±  0.372   #/op
     MatrixCopy.colFirst:·instructions            avgt    5  35.888 ±  1.215   #/op

     MatrixCopy.rowFirst                          avgt   25   2.662 ±  0.003  ns/op
     MatrixCopy.rowFirst:·CPI                     avgt    5   0.312 ±  0.003   #/op
     MatrixCopy.rowFirst:·L1-dcache-load-misses   avgt    5   0.066 ±  0.001   #/op
     MatrixCopy.rowFirst:·L1-dcache-loads         avgt    5  14.570 ±  0.400   #/op
     MatrixCopy.rowFirst:·LLC-loads               avgt    5   0.002 ±  0.001   #/op
     MatrixCopy.rowFirst:·cycles                  avgt    5  11.046 ±  0.343   #/op
     MatrixCopy.rowFirst:·instructions            avgt    5  35.416 ±  1.248   #/op

     So, when comparing two different benchmarks, you have to follow up if the difference is caused
     by the memory locality issues.
  */

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_37
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(".*" + CacheAccess.class.getSimpleName() + ".*")
                      .build();

    new Runner(opt).run();
  }
}
