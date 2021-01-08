package threads;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("PMD.SystemPrintln")
public final class GZipAllFiles {
  public static final int THREAD_COUNT = 4;
  private static ExecutorService pool =
      Executors.newFixedThreadPool(THREAD_COUNT);

  private GZipAllFiles() {
    throw new IllegalStateException("Private constructor invoked for class: "
                                    + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    for (String filename: args) {
      zipFile(filename, pool);
    }
    pool.shutdown();
  }

  private static void zipFile(String filename, ExecutorService pool) {
    File f = new File(filename);
    if (f.exists())
      zip(f, pool);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void zip(File f, ExecutorService pool) {
    if (f.isDirectory()) {
      Optional<?> objs = Optional.ofNullable(f.listFiles());
      objs.ifPresent(obj -> {
        File[] files = (File[])obj;
        for (File file: files) {
          // don't recurse directories
          if (!file.isDirectory())
            submitZipTask(file, pool);
        }
      });
    } else
      submitZipTask(f, pool);
  }

  private static void submitZipTask(File file, ExecutorService pool) {
    Runnable task = new GZipRunnable(file);
    pool.submit(task);
  }
}
