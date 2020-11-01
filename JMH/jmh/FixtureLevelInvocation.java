package jmh;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

/**
 * Fixtures have different Levels to control when they are about to run. Level.Invocation is useful
 * sometimes to do some per-invocation work which should not count as payload (e.g. sleep for some
 * time to emulate think time)
 */
@SuppressWarnings({"PMD.CommentSize", "PMD.LawOfDemeter"})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class FixtureLevelInvocation {

  /*
   * Fixtures have different Levels to control when they are about to run.
   * Level.Invocation is useful sometimes to do some per-invocation work,
   * which should not count as payload. PLEASE NOTE the timestamping and
   * synchronization for Level.Invocation helpers might significantly offset
   * the measurement, use with care. See Level.Invocation javadoc for further
   * discussion.
   *
   * Consider this sample:
   */

  /*
   * ============================== HOW TO RUN THIS TEST: ====================================
   *
   * You can see the cold scenario is running longer, because we pay for
   * thread wakeups.
   *
   * You can run this test:
   *
   * a) Via the command line:
   *    $ mvn clean install
   *    $ java -jar target/benchmarks.jar JMHSample_07 -f 1
   *    (we requested single fork; there are also other options, see -h)
   *
   * b) Via the Java API:
   *    (see the JMH homepage for possible caveats when running from IDE:
   *      http://openjdk.java.net/projects/code-tools/jmh/)
   */

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(FixtureLevelInvocation.class.getSimpleName())
                      .forks(1)
                      .build();

    new Runner(opt).run();
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  public double measureHot(NormalState e, final Scratch s)
      throws ExecutionException, InterruptedException {
    return e.service.submit(new Task(s)).get();
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  public double measureCold(LaggingState e, final Scratch s)
      throws ExecutionException, InterruptedException {
    return e.service.submit(new Task(s)).get();
  }

  /*
   * This state handles the executor.
   * Note we create and shutdown executor with Level.Trial, so
   * it is kept around the same across all iterations.
   */

  @State(Scope.Benchmark)
  public static class NormalState {
    ExecutorService service;

    @Setup(Level.Trial)
    public void up() {
      service = Executors.newCachedThreadPool();
    }

    @TearDown(Level.Trial)
    public void down() {
      service.shutdown();
    }
  }

  /*
   * This is the *extension* of the basic state, which also
   * has the Level.Invocation fixture method, sleeping for some time.
   */

  public static class LaggingState extends NormalState {
    public static final int SLEEP_TIME = Integer.getInteger("sleepTime", 10);

    @Setup(Level.Invocation)
    public void lag() throws InterruptedException {
      TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
    }
  }

  /*
   * This allows us to formulate the task: measure the task turnaround in
   * "hot" mode when we are not sleeping between the submits, and "cold" mode,
   * when we are sleeping.
   */

  /*
   * This is our scratch state which will handle the work.
   */

  @State(Scope.Thread)
  public static class Scratch {
    private double p;

    public double doWork() {
      p = Math.log(p);
      return p;
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  public static class Task implements Callable<Double> {
    private final Scratch s;

    public Task(Scratch s) {
      this.s = s;
    }

    @Override
    public Double call() {
      return s.doWork();
    }
  }
}
