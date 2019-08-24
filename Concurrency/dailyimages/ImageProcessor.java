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
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * * This sample courtesy https://www.javaspecialists.eu/archive/Issue271.htm
 */
public class ImageProcessor {
  public static final int NUMBER_TO_SHOW = 1000;
  public static final int DELAY = 100;  // ms between requests
  private final CountDownLatch latch = new CountDownLatch(NUMBER_TO_SHOW);
  private ExecutorService executor1 =
      (ExecutorService)Executors.newCachedThreadPool(
          new NamedThreadFactory("executor1"));
  private ExecutorService executor2 =
      Executors.newFixedThreadPool(100, new NamedThreadFactory("executor2"));
  private boolean printMessage = true;
  private boolean saveFile = true;
  private AtomicInteger failureCount = new AtomicInteger(0);
  private Path imageDir = Paths.get("/tmp/images");

  private final HttpClient client =
      HttpClient.newBuilder()
          .executor(executor1)
          .followRedirects(HttpClient.Redirect.NEVER)
          .build();

  public <T> CompletableFuture<T> getAsync(
      String url,
      HttpResponse.BodyHandler<T> responseBodyHandler) {
    HttpRequest request = HttpRequest.newBuilder()
                              .GET()
                              .uri(URI.create(url))
                              .timeout(Duration.ofSeconds(30))
                              .build();
    if (executor2.isShutdown())
    return client.sendAsync(request, responseBodyHandler)
        .thenApply(HttpResponse::body);
    else
    return client.sendAsync(request, responseBodyHandler)
        .thenApplyAsync(HttpResponse::body, executor2);

  }

  public CompletableFuture<ImageInfo> findImageInfo(LocalDate date,
                                                    ImageInfo info) {
    return getAsync(info.getUrlForDate(date),
                    HttpResponse.BodyHandlers.ofString())
        .thenApply(info::findImage);
  }

  public CompletableFuture<ImageInfo> findImageData(ImageInfo info) {
    return getAsync(info.getImagePath(),
                    HttpResponse.BodyHandlers.ofByteArray())
        .thenApply(info::setImageData);
  }

  public void load(LocalDate date, ImageInfo info) {
    findImageInfo(date, info)
        .thenCompose(this::findImageData)
        .thenAccept(this::process)
        .exceptionally(t -> {
          System.err.println(info.getUrlForDate(date) + " : " + t);
          failureCount.incrementAndGet();
          return null;
        })
        .thenAccept(t -> latch.countDown());
  }

  public void loadAll() {
    long time = System.nanoTime();
    try {
      LocalDate date = LocalDate.now();
      Random random = new Random();
      boolean isDilbert = random.nextBoolean();
      System.out.println("isDilbert: " + isDilbert);
      for (int i = 0; i < NUMBER_TO_SHOW; i++) {
        ImageInfo info;
        if (isDilbert)
          info = new DilbertImageInfo();
        else
          info = new WikimediaImageInfo();
        info.setDate(date.toString());
        System.out.println("Loading " + date);
        load(date, info);
        if (DELAY > 0)
          Thread.sleep(DELAY);
        date = date.minusDays(1);
      }
      latch.await();

        executor1.shutdown();
        executor1.awaitTermination(1, TimeUnit.DAYS);
    //    Thread.sleep(2000);
        executor2.shutdown();
        executor2.awaitTermination(1, TimeUnit.DAYS);
      
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Interrupted");
    } finally {
      time = System.nanoTime() - time;
      System.out.printf("time = %dms%n", time / 1_000_000);
      System.out.println(failureCount.get() + " failures downloading");
    }
  }

  public void process(ImageInfo info) {
    latch.countDown();
    if (printMessage) {
      System.out.println("process called by " + Thread.currentThread()
                         + ", date: " + info.getDate());
    }
    if (saveFile)
      try {
        Files.createDirectories(imageDir);
        Files.write(imageDir.resolve(info.getDate() + ".jpg"),
                    info.getImageData());
      } catch (IOException ex) {
        System.err.println(ex);
      }
  }

  public static void main(String... args) {
    ImageProcessor processor = new ImageProcessor();
    processor.loadAll();
  }
}
