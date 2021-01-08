package dailyimages;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/** This sample courtesy https://www.javaspecialists.eu/archive/Issue271.htm. */
public class ImageProcessor implements Runnable {
  private static final java.util.logging.Logger LOG =
      java.util.logging.Logger.getLogger(ImageProcessor.class.getName());
  public static final int NUMBER_TO_SHOW = 100;
  public static final int MAX_CONCURRENT_STREAMS = 20;
  public static final int MAX_THREADS = 20;
  public static final int DELAY = 100;
  private static final boolean PRINT_MESSAGE = true;
  private static final boolean SAVE_FILE = true;
  private static boolean isDilbert;
  // ms between requests
  private final CountDownLatch latch = new CountDownLatch(NUMBER_TO_SHOW);
  private final ExecutorService executor1 =
      Executors.newCachedThreadPool(new NamedThreadFactory("executor1"));
  private final ExecutorService executor2 =
      Executors.newCachedThreadPool(new NamedThreadFactory("executor2"));
  private final AtomicInteger failureCount = new AtomicInteger(0);
  private final Path imageDir = Paths.get("/tmp/images");
  private final HttpClient client =
      HttpClient.newBuilder()
          .executor(executor1)
          .followRedirects(HttpClient.Redirect.NEVER)
          .connectTimeout(Duration.ofSeconds(30))
          .build();
  private boolean finished;
  private Random random = new Random();

  @Override
  public void run() {
    loadAll();
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public <T> CompletableFuture<T> getAsync(
      String url,
      HttpResponse.BodyHandler<T> responseBodyHandler) {
    HttpRequest request = HttpRequest.newBuilder()
                              .GET()
                              .uri(URI.create(url))
                              .timeout(Duration.ofSeconds(5))
                              .build();
    return client.sendAsync(request, responseBodyHandler)
        .exceptionally(t -> {
          LOG.severe(() -> request + " failed with exception : " + t);
          failureCount.incrementAndGet();
          latch.countDown();
          return null;
        })
        .thenApplyAsync(HttpResponse::body, executor2);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public CompletableFuture<ImageInfo> findImageInfo(LocalDate date,
                                                    ImageInfo info) {
    return getAsync(info.getUrlForDate(date),
                    HttpResponse.BodyHandlers.ofString())
        .thenApply(info::findImage);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public CompletableFuture<ImageInfo> findImageData(ImageInfo info) {
    return getAsync(info.getImagePath(),
                    HttpResponse.BodyHandlers.ofByteArray())
        .thenApply(info::setImageData);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void load(LocalDate date, ImageInfo info) throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(random.nextInt(DELAY));
    findImageInfo(date, info)
        .thenCompose(this::findImageData)
        .thenAccept(this::process)
        .exceptionally(t -> {
          LOG.severe(() -> info.getUrlForDate(date) + " : " + t);
          failureCount.incrementAndGet();
          latch.countDown();
          return null;
        })
        .thenAccept(t -> latch.countDown());
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public void loopLoadAll(boolean isDilbert) throws InterruptedException {
    LocalDate newDate = LocalDate.now();
    for (int i = 0; i < NUMBER_TO_SHOW; i++) {
      final ImageInfo info;
      if (isDilbert)
        info = new DilbertImageInfo();
      else
        info = new WikimediaImageInfo();
      info.setDate(newDate.toString());
      LOG.info(() -> "Loading " + info.getDate());
      load(newDate, info);
      newDate = newDate.minusDays(1);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void loadAll() {
    long startTime = System.nanoTime();
    try {
      loopLoadAll(isDilbert);
      latch.await(3, TimeUnit.MINUTES);
      LOG.info("PAST LATCH");
      executor1.shutdown();
      executor2.shutdown();
      LOG.info("PAST SHUTDOWN");
      if (!executor1.awaitTermination(1, TimeUnit.MINUTES))
        executor1.shutdownNow();
      if (!executor2.awaitTermination(1, TimeUnit.MINUTES))
        executor2.shutdownNow();
      LOG.info("PAST TERMINATION and SHUTDOWNNOW");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOG.severe("Interrupted");
    } finally {
      final long endTime = System.nanoTime() - startTime;
      LOG.info(() -> String.format("time = %dms%n", endTime / 1_000_000));
      LOG.info(() -> failureCount.get() + " failures downloading");
      finished = true;
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void process(ImageInfo info) {
    LOG.info(() -> "processing " + info);
    latch.countDown();
    String infoDate = info.getDate();
    if (PRINT_MESSAGE) {
      LOG.info(()
                   -> "process called by " + Thread.currentThread()
                          + ", date: " + infoDate);
    }
    if (SAVE_FILE)
      try {
        LOG.info(() -> "saving " + info);
        Files.createDirectories(imageDir);
        Files.write(imageDir.resolve(infoDate + ".jpg"), info.getImageData());
      } catch (IOException ex) {
        LOG.severe(() -> ex.getMessage());
      }
  }

  public static void main(String... args) throws InterruptedException {
    if (args.length > 0)
      isDilbert = true;
    ImageProcessor processor = new ImageProcessor();
    Thread thread = new Thread(processor);
    thread.start();
    Thread threadTracer = new Thread(new ThreadTrace(processor));
    threadTracer.start();
    LOG.info("About to call join");
    thread.join(3 * 60 * 1000);
    LOG.info("PROGRAM TERMINATED");
  }

  private static class ThreadTrace implements Runnable {
    ImageProcessor proc;

    ThreadTrace(ImageProcessor proc) {
      this.proc = proc;
    }

    @Override
    public void run() {
      while (!proc.finished) {
        try {
          Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
          map.keySet().forEach(t
                               -> LOG.info(t.getName() + "\tIs Daemon "
                                           + t.isDaemon() + "\tIs Alive "
                                           + t.isAlive()));
          LOG.info("Number of threads: " + map.size());
          LOG.info("Latch value: " + proc.latch.getCount());
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
