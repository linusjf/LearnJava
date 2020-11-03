package jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.ThreadParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@SuppressWarnings("all")
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class InfraParams {

  /*
   * There is a way to query JMH about the current running mode. This is
   * possible with three infrastructure objects we can request to be injected:
   *   - BenchmarkParams: covers the benchmark-global configuration
   *   - IterationParams: covers the current iteration configuration
   *   - ThreadParams: covers the specifics about threading
   *
   * Suppose we want to check how the ConcurrentHashMap scales under different
   * parallelism levels. We can put concurrencyLevel in @Param, but it sometimes
   * inconvenient if, say, we want it to follow the @Threads count. Here is
   * how we can query JMH about how many threads was requested for the current run,
   * and put that into concurrencyLevel argument for CHM constructor.
   */

  static final int THREAD_SLICE = 1000;

  private ConcurrentHashMap<String, String> mapSingle;
  private ConcurrentHashMap<String, String> mapFollowThreads;

  @Setup
  public void setup(BenchmarkParams params) {
    int capacity = 16 * THREAD_SLICE * params.getThreads();
    mapSingle = new ConcurrentHashMap<>(capacity, 0.75f, 1);
    mapFollowThreads =
        new ConcurrentHashMap<>(capacity, 0.75f, params.getThreads());
  }

  /*
   * Here is another neat trick. Generate the distinct set of keys for all threads:
   */

  @State(Scope.Thread)
  public static class Ids {
    private List<String> ids;

    @Setup
    public void setup(ThreadParams threads) {
      ids = new ArrayList<>();
      for (int c = 0; c < THREAD_SLICE; c++) {
        ids.add("ID" + (THREAD_SLICE * threads.getThreadIndex() + c));
      }
    }
  }

  @Benchmark
  public void measureDefault(Ids ids) {
    for (String s: ids.ids) {
      mapSingle.remove(s);
      mapSingle.put(s, s);
    }
  }

  @Benchmark
  public void measureFollowThreads(Ids ids) {
    for (String s: ids.ids) {
      mapFollowThreads.remove(s);
      mapFollowThreads.put(s, s);
    }
  }

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_31 -t 4 -f 5
   *    (we requested 4 threads, and 5 forks; there are also other options, see -h)
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(InfraParams.class.getSimpleName())
                      .threads(4)
                      .forks(5)
                      .build();
    new Runner(opt).run();
  }
}
