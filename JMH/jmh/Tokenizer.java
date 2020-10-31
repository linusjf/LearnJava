package jmh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
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

@State(Scope.Thread)
public class Tokenizer {

  String sample;
  int tokenCount;
  int splitCount;
  int splitRegexCount;

  @Setup(Level.Trial)
  public void doSetup() {
    StringBuilder sb = new StringBuilder();
    for (int i = 100_000; i < 100_000 + 60; i++) {
      sb.append(i).append(' ');
      if (i % 5 == 0)
        sb.append("  ");
    }
    sample = sb.toString();
  }

  @TearDown(Level.Trial)
  public void doTearDown() {
    sample = null;
    if (tokenCount > 0)
      System.out.println("Token count: " + tokenCount);
    if (splitCount > 0)
      System.out.println("Split count: " + splitCount);
    if (splitRegexCount > 0)
      System.out.println("Split regex count: " + splitRegexCount);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void measureStringTokenizer(Tokenizer t) {
    StringTokenizer st = new StringTokenizer(t.sample, " ");
    List<String> list = new ArrayList<>();
    while (st.hasMoreTokens())
      list.add(st.nextToken());
    t.tokenCount = list.size();
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void measureStringSplitRegex(Tokenizer t) {
    String[] tokens = t.sample.split("\\s+");
    List<String> list = Arrays.asList(tokens);
    t.splitRegexCount = list.size();
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  public void measureStringSplit(Tokenizer t) {
    String[] tokens = t.sample.split(" ");
    List<String> list = Arrays.asList(tokens);
    t.splitCount = list.size();
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(Tokenizer.class.getSimpleName())
                      .forks(1)
                      .build();
    new Runner(opt).run();
  }
}
