package threads;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class GZipAllFiles {
  public static final int THREAD_COUNT = 4;

  private GZipAllFiles() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
    for (String filename : args) {
      File f = new File(filename);
      if (f.exists()) {
        zip(f, pool);
      }
    }
    pool.shutdown();
  }

  private static void zip(File f, ExecutorService pool) {
    if (f.isDirectory()) {
      File[] files = f.listFiles();
      for (File file : files) {
        if (!file.isDirectory()) {  // don't recurse directories
          Runnable task = new GZipRunnable(file);
          pool.submit(task);
        }
      }
    } else {
      Runnable task = new GZipRunnable(f);
      pool.submit(task);
    }
  }
}
