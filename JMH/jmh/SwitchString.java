package jmh;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

@SuppressWarnings("all")
@State(Scope.Thread)
public class SwitchString {

  private static final int SIZE = 1000;

  volatile String[] months;
  volatile String[] codedStrings;
  volatile int[] indexes;
  volatile int[] codedIndexes;
  volatile Random random;

  @Setup(Level.Trial)
  public void doSetup() {
    months = new String[] {"January",
                           "February",
                           "March",
                           "April",
                           "May",
                           "June",
                           "July",
                           "August",
                           "September",
                           "October",
                           "November",
                           "December"};
    codedStrings = new String[] {"AaAa", "BBBB", "AaBB", "BBAa"};
    random = new Random(123456789L);
    indexes = new int[SIZE];
    for (int i = 0; i < SIZE; i++)
      indexes[i] = random.nextInt(12);
    random = new Random(987654321L);
    codedIndexes = new int[SIZE];
    for (int i = 0; i < SIZE; i++)
      codedIndexes[i] = random.nextInt(4);
  }

  @TearDown(Level.Trial)
  public void doTearDown() {
    months = null;
    indexes = null;
    random = null;
    codedIndexes = null;
    codedStrings = null;
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OperationsPerInvocation(SIZE)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void ifElseRandom(Blackhole bh) {
    for (int i = 0; i < SIZE; i++) {
      int month = 0;
      String value = months[indexes[i]];
      if (value.equals("January"))
        month = 1;
      else if (value.equals("February"))
        month = 2;
      else if (value.equals("March"))
        month = 3;
      else if (value.equals("April"))
        month = 4;
      else if (value.equals("May"))
        month = 5;
      else if (value.equals("June"))
        month = 6;
      else if (value.equals("July"))
        month = 7;
      else if (value.equals("August"))
        month = 8;
      else if (value.equals("September"))
        month = 9;
      else if (value.equals("October"))
        month = 10;
      else if (value.equals("November"))
        month = 11;
      else if (value.equals("December"))
        month = 12;
      bh.consume(month);
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OperationsPerInvocation(SIZE)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void switchCaseRandom(Blackhole bh) {
    for (int i = 0; i < SIZE; i++) {
      int month = 0;
      String value = months[indexes[i]];
      switch (value) {
        case "January":
          month = 1;
          break;

        case "February":
          month = 2;
          break;

        case "March":
          month = 3;
          break;

        case "April":
          month = 4;
          break;

        case "May":
          month = 5;
          break;

        case "June":
          month = 6;
          break;

        case "July":
          month = 7;
          break;

        case "August":
          month = 8;
          break;

        case "September":
          month = 9;
          break;

        case "October":
          month = 10;
          break;

        case "November":
          month = 11;
          break;

        case "December":
          month = 12;
          break;
      }
      bh.consume(month);
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OperationsPerInvocation(SIZE)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void ifElseRandomHash(Blackhole bh) {
    for (int i = 0; i < SIZE; i++) {
      int idx = -1;
      String value = codedStrings[codedIndexes[i]];
      if (value.equals("AaAa"))
        idx = 0;
      else if (value.equals("BBBB"))
        idx = 1;
      else if (value.equals("AaBB"))
        idx = 2;
      else if (value.equals("BBAa"))
        idx = 3;
      bh.consume(idx);
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OperationsPerInvocation(SIZE)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void switchCaseRandomHash(Blackhole bh) {
    for (int i = 0; i < SIZE; i++) {
      int idx = -1;
      String value = codedStrings[codedIndexes[i]];
      switch (value) {
        case "AaAa":
          idx = 0;
          break;

        case "BBBB":
          idx = 1;
          break;

        case "AaBB":
          idx = 2;
          break;

        case "BBAa":
          idx = 3;
          break;
      }
      bh.consume(idx);
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(SwitchString.class.getSimpleName())
                      .verbosity(VerboseMode.EXTRA)
                      .forks(0)
                      .build();
    new Runner(opt).run();
  }
}
