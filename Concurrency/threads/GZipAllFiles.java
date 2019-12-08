package threads;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class GZipAllFiles {
  public static final int THREAD_COUNT = 4;

  private GZipAllFiles() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
      for (String filename: args)
        zipFile(filename, pool);
      pool.shutdown();
    } catch (InterruptedException | ExecutionException ie) {
      System.err.println(ie.getMessage());
    }
  }

  private static void zipFile(String filename, ExecutorService pool)
      throws InterruptedException, ExecutionException {
    File f = new File(filename);
    if (f.exists())
      zip(f, pool);
  }

  private static void zip(File f, ExecutorService pool)
      throws ExecutionException, InterruptedException {
    if (f.isDirectory()) {
      Optional<Object> files = Optional.ofNullable(f.listFiles());
      if (files.isPresent()) {
        File[] objs = (File[])files.get();
        for (File file: objs) {
          if (!file.isDirectory())
            // don't recurse directories
            submitZipTask(file, pool);
        }
      }
    } else
      submitZipTask(f, pool);
  }

  private static void submitZipTask(File file, ExecutorService pool)
      throws ExecutionException, InterruptedException {
    Runnable task = new GZipRunnable(file);
    assert pool.submit(task).get() != null;
  }
}
