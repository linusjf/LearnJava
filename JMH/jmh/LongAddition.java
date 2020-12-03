package jmh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("all")
public class LongAddition {
  private static AtomicLong count = new AtomicLong();
  private static LongAdder longAdder = new LongAdder();
  private static AtomicLong count20 = new AtomicLong();
  private static LongAdder longAdder20 = new LongAdder();

  // How many threads are started per second by default for testing
  @Benchmark
  @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Threads(1)
  public void atolong() {
    // Testing AtomicLong Incremental Method
    count.getAndIncrement();
  }

  // How many threads are started per second by default for testing
  @Benchmark
  @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Threads(1)
  public void loadder() {
    // Testing LongAdder Incremental Method
    longAdder.increment();
  }

  // How many threads are started per second by default for testing
  @Benchmark
  @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Threads(20)
  public void atolong20() {
    // Testing AtomicLong Incremental Method
    count20.getAndIncrement();
  }

  // How many threads are started per second by default for testing
  @Benchmark
  @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Threads(20)
  public void loadder20() {
    // Testing LongAdder Incremental Method
    longAdder20.increment();
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
                          .include(LongAddition.class.getSimpleName())
                          .forks(1)
                          .build();
    new Runner(options).run();
  }
}
