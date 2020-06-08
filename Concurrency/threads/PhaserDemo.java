package threads;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public enum PhaserDemo {
  ;

  public static void main(String[] args) {
    Phaser phaser = new Phaser(3);
    FileSearch system = new FileSearch(
        "/data/data/com.termux/files/home/LearnJava", "log", phaser);
    FileSearch apps = new FileSearch(
        "/data/data/com.termux/files/home/LearnNodeJS", "log", phaser);
    FileSearch documents =
        new FileSearch("/data/data/com.termux/files/home/LearnCS", "log", phaser);
    Thread systemThread = new Thread(system, "System");
    systemThread.start();
    Thread appsThread = new Thread(apps, "Apps");
    appsThread.start();
    Thread documentsThread = new Thread(documents, "Documents");
    documentsThread.start();
    try {
      systemThread.join();
      appsThread.join();
      documentsThread.join();
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.println("Terminated: " + phaser.isTerminated());
  }

  static class FileSearch implements Runnable {
    private final String initPath;
    private final String end;
    private List<String> results;
    private final Phaser phaser;

    FileSearch(String initPath, String end, Phaser phaser) {
      this.initPath = initPath;
      this.end = end;
      this.phaser = phaser;
      results = new ArrayList<>();
    }

    private void directoryProcess(File file) {
      File[] list = file.listFiles();
      if (list != null) {
        for (File f: list) {
          if (f.isDirectory()) {
            directoryProcess(f);
          } else {
            fileProcess(f);
          }
        }
      }
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    private void fileProcess(File file) {
      if (file.getName().endsWith(end))
        results.add(file.getAbsolutePath());
    }

    @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
    private void filterResults() {
      List<String> newResults = new ArrayList<>();
      long actualDate = new Date().getTime();
      for (String fileName: results) {
        long fileDate = new File(fileName).lastModified();
        if (actualDate - fileDate
            < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
          newResults.add(fileName);
      }
      results = newResults;
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    private boolean checkResults() {
      if (results.isEmpty()) {
        System.out.printf("%s: Phase %d: 0 results.%n",
                          Thread.currentThread().getName(),
                          phaser.getPhase());
        System.out.printf("%s: Phase %d: End.%n",
                          Thread.currentThread().getName(),
                          phaser.getPhase());
        phaser.arriveAndDeregister();
        return false;
      } else {
        System.out.printf("%s: Phase %d: %d results.%n",
                          Thread.currentThread().getName(),
                          phaser.getPhase(),
                          results.size());
        phaser.arriveAndAwaitAdvance();
        return true;
      }
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    private void showInfo() {
      for (String fileName: results) {
        File file = new File(fileName);
        System.out.printf("%s: %s%n",
                          Thread.currentThread().getName(),
                          file.getAbsolutePath());
      }
      phaser.arriveAndAwaitAdvance();
    }

    @SuppressWarnings({"checkstyle:returncount", "PMD.LawOfDemeter"})
    @Override
    public void run() {
      phaser.arriveAndAwaitAdvance();
      System.out.printf("%s: Starting.%n", Thread.currentThread().getName());
      File file = new File(initPath);
      if (file.isDirectory())
        directoryProcess(file);
      if (!checkResults())
        return;
      filterResults();
      if (!checkResults())
        return;
      showInfo();
      phaser.arriveAndDeregister();
      System.out.printf("%s: Work completed.%n",
                        Thread.currentThread().getName());
    }
  }
}
