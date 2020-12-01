package jmh;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
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
import org.openjdk.jmh.runner.options.VerboseMode;

@SuppressWarnings("all")
@State(Scope.Thread)
public class BranchPrediction {

  volatile int[] sortedData;
  volatile int[] unsortedData;

  @Setup(Level.Trial)
  public void doSetup() {
    int data[] = new int[32768];
    Random rnd = new Random(0);
    for (int c = 0; c < data.length; ++c)
      data[c] = rnd.nextInt() % 256;
    unsortedData = data.clone();
    Arrays.sort(data);
    sortedData = data;
  }

  @TearDown(Level.Trial)
  public void doTearDown() {
    unsortedData = null;
    sortedData = null;
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public long withoutSorting() {
    long sum = 0;
    for (int c = 0; c < unsortedData.length; ++c)
      if (unsortedData[c] >= 128)
        sum += unsortedData[c];
    return sum;
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public long withSorting() {
    long sum = 0;
    for (int c = 0; c < sortedData.length; ++c)
      if (sortedData[c] >= 128)
        sum += sortedData[c];
    return sum;
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(BranchPrediction.class.getSimpleName())
                      .verbosity(VerboseMode.EXTRA)
                      .forks(0)
                      .build();
    new Runner(opt).run();
  }
}
