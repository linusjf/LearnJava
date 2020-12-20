package jmh;

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
public class SwitchString {

  volatile String value;

  @Setup(Level.Trial)
  public void doSetup() {
    value = "July";
  }

  @TearDown(Level.Trial)
  public void doTearDown() {
    value = null;
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public int ifElse() {
    int month = 0;
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
    return month;
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public int switchCase() {
    int month = 0;
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
    return month;
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
