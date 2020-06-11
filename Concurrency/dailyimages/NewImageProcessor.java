package dailyimages;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicInteger;

/** This sample courtesy https://www.javaspecialists.eu/archive/Issue271.htm. */
public class NewImageProcessor {
  public static final int NUMBER_TO_SHOW = 100;
  public static final int MAX_CONCURRENT_STREAMS = 10;
  public static final int DELAY = 100;
  private static final boolean PRINT_MESSAGE = true;
  private static final boolean SAVE_FILE = true;
  private static boolean isDilbert;
  // ms between requests
  private final CountDownLatch latch = new CountDownLatch(NUMBER_TO_SHOW);
  
BlockingQueue<Runnable> boundedQueue = new ArrayBlockingQueue<Runnable>(NUMBER_TO_SHOW);
private ExecutorService executor1 = new ThreadPoolExecutor(NUMBER_TO_SHOW / 2, NUMBER_TO_SHOW, 60, TimeUnit.SECONDS, boundedQueue, new AbortPolicy());
BlockingQueue<Runnable> boundedQueue2 = new ArrayBlockingQueue<>(NUMBER_TO_SHOW);
  private final ExecutorService executor2 =
      new ThreadPoolExecutor(NUMBER_TO_SHOW / 2, NUMBER_TO_SHOW, 60, TimeUnit.SECONDS, boundedQueue2, new AbortPolicy());
BlockingQueue<Runnable> boundedQueue3 = new ArrayBlockingQueue<>(NUMBER_TO_SHOW);
  private final ExecutorService executor3 =
      new ThreadPoolExecutor(NUMBER_TO_SHOW / 2, NUMBER_TO_SHOW, 60, TimeUnit.SECONDS, boundedQueue3, new AbortPolicy());
  private final AtomicInteger failureCount = new AtomicInteger(0);
  private final Path imageDir = Paths.get("/tmp/images");
  private final HttpClient client =
      HttpClient.newBuilder()
      .version(Version.HTTP_2)
          .executor(executor1)
          .followRedirects(HttpClient.Redirect.NEVER)
          .build();

  @SuppressWarnings("PMD.LawOfDemeter")
  public <T> CompletableFuture<T> getAsync2(
      String url,
      HttpResponse.BodyHandler<T> responseBodyHandler) {
    HttpRequest request = 
      HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(url))
      .build();
      return client.sendAsync(request, responseBodyHandler)
          .thenApplyAsync(HttpResponse::body, executor3);
  }
  @SuppressWarnings("PMD.LawOfDemeter")
  public <T> CompletableFuture<T> getAsync(
      String url,
      HttpResponse.BodyHandler<T> responseBodyHandler) {
    HttpRequest request = 
      HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(url))
      .build();
      return client.sendAsync(request, responseBodyHandler)
          .thenApplyAsync(HttpResponse::body, executor2);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public CompletableFuture<ImageInfo> findImageInfo(LocalDate date,
                                                    ImageInfo info) {
    System.out.println("Finding image info: " + info);
    return getAsync(info.getUrlForDate(date),
                    HttpResponse.BodyHandlers.ofString())
        .thenApply(info::findImage);
  }

  public void printExecutors() {
    System.out.println("Executor 1: " + executor1);
    System.out.println("Executor 2: " + executor2);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public CompletableFuture<ImageInfo> findImageData(ImageInfo info) {
    System.out.println("Finding image data: " + info);
    return getAsync2(info.getImagePath(),
                    HttpResponse.BodyHandlers.ofByteArray())
        .thenApply(info::setImageData);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void load(LocalDate date, ImageInfo info) {
    findImageInfo(date, info)
        .thenCompose(this::findImageData)
        .thenAccept(this::process)
        .exceptionally(t -> {
          System.err.println(info.getUrlForDate(date) + " : " + t);
          failureCount.incrementAndGet();
          return null;
        })
        .whenComplete((x, t) -> latch.countDown());
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public void loopLoadAll(boolean isDilbert) throws InterruptedException {
    LocalDate newDate = LocalDate.now();
    for (int i = 0; i < NUMBER_TO_SHOW; i++) {
      ImageInfo info;
      if (isDilbert)
        info = new DilbertImageInfo();
      else
        info = new WikimediaImageInfo();
      info.setDate(newDate.toString());
      System.out.println("Loading " + newDate);
      load(newDate, info);
      if (DELAY > 0)
        TimeUnit.MILLISECONDS.sleep(DELAY);
      newDate = newDate.minusDays(1);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void loadAll() {
    long time = System.nanoTime();
    try {
      loopLoadAll(isDilbert);
      latch.await();
      System.out.println("PAST LATCH");
      // wait for a minute  before shutting down executor2
      // http timeouts must expire first
      executor1.shutdown();
      executor1.awaitTermination(1, TimeUnit.MINUTES);
      executor2.shutdown();
      executor2.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Interrupted");
    } finally {
      time = System.nanoTime() - time;
      System.out.printf("time = %dms%n", time / 1000000);
      System.out.println(failureCount.get() + " failures downloading");
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void process(ImageInfo info) {
    latch.countDown();
    String infoDate = info.getDate();
    if (PRINT_MESSAGE) {
      System.out.println("process called by " + Thread.currentThread()
                         + ", date: " + infoDate);
    }
    if (SAVE_FILE)
      try {
        Files.createDirectories(imageDir);
        Files.write(imageDir.resolve(infoDate + ".jpg"),
                    info.getImageData());
      System.out.println("file created for "
          + infoDate);
      } catch (IOException ex) {
        System.err.println(ex);
      }
  }

  public static void main(String... args) {
    if (args.length > 0)
      isDilbert = true;
    NewImageProcessor processor = new NewImageProcessor();
    processor.printExecutors();
    processor.loadAll();
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof NewImageProcessor))
      return false;
    NewImageProcessor other = (NewImageProcessor)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$latch = this.latch;
    Object other$latch = other.latch;
    if (this$latch == null ? other$latch != null
                           : !this$latch.equals(other$latch))
      return false;
    Object this$executor1 = this.executor1;
    Object other$executor1 = other.executor1;
    if (this$executor1 == null ? other$executor1 != null
                               : !this$executor1.equals(other$executor1))
      return false;
    Object this$executor2 = this.executor2;
    Object other$executor2 = other.executor2;
    if (this$executor2 == null ? other$executor2 != null
                               : !this$executor2.equals(other$executor2))
      return false;
    Object this$failureCount = this.failureCount;
    Object other$failureCount = other.failureCount;
    if (this$failureCount == null
            ? other$failureCount != null
            : !this$failureCount.equals(other$failureCount))
      return false;
    Object this$imageDir = this.imageDir;
    Object other$imageDir = other.imageDir;
    if (this$imageDir == null ? other$imageDir != null
                              : !this$imageDir.equals(other$imageDir))
      return false;
    Object this$client = this.client;
    Object other$client = other.client;
    if (this$client == null ? other$client != null
                            : !this$client.equals(other$client))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof NewImageProcessor;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $latch = this.latch;
    result = result * PRIME + ($latch == null ? 43 : $latch.hashCode());
    Object $executor1 = this.executor1;
    result = result * PRIME + ($executor1 == null ? 43 : $executor1.hashCode());
    Object $executor2 = this.executor2;
    result = result * PRIME + ($executor2 == null ? 43 : $executor2.hashCode());
    Object $failureCount = this.failureCount;
    result = result * PRIME
             + ($failureCount == null ? 43 : $failureCount.hashCode());
    Object $imageDir = this.imageDir;
    result = result * PRIME + ($imageDir == null ? 43 : $imageDir.hashCode());
    Object $client = this.client;
    result = result * PRIME + ($client == null ? 43 : $client.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "ImageProcessor(latch=" + this.latch
        + ", executor1=" + this.executor1 + ", executor2=" + this.executor2
        + ", failureCount=" + this.failureCount + ", imageDir=" + this.imageDir
        + ", client=" + this.client + ")";
  }
}
