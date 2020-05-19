package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public enum ForkJoinResultDemo {
  ;
  private static final String WORD = "the";
  private static final int MIN_LINES = 10;
  private static final int MIN_WORDS = 100;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    DocumentMock mock = new DocumentMock();
    String[][] document = mock.generateDocument(100, 1000, WORD);
    DocumentTask task = new DocumentTask(document, 0, 100, WORD);
    ForkJoinPool pool = new ForkJoinPool();
    pool.execute(task);
    do {
      System.out.printf("******************************************%n");
      System.out.printf("Main: Parallelism: %d%n", pool.getParallelism());
      System.out.printf("Main: Active Threads: %d%n", pool.getActiveThreadCount());
      System.out.printf("Main: Task Count: %d%n", pool.getQueuedTaskCount());
      System.out.printf("Main: Steal Count: %d%n", pool.getStealCount());
      System.out.printf("******************************************%n");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    } while (!task.isDone());
    pool.shutdown();
    try {
      pool.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    try {
      System.out.printf(
          "Main: The word '" + WORD + "' appears %d times in the document.", task.get());
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e);
    }
  }

  static class DocumentMock {
    private final String[] words = {
      "the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main",
    };

    @SuppressWarnings({"PMD.AvoidArrayLoops", "PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
    public String[][] generateDocument(int numLines, int numWords, String word) {
      int counter = 0;
      String[][] document = new String[numLines][numWords];
      Random random = new Random();

      for (int i = 0; i < numLines; i++) {
        for (int j = 0; j < numWords; j++) {
          int index = random.nextInt(words.length);
          document[i][j] = words[index];
          if (document[i][j].equals(word)) {
            counter++;
          }
        }
      }
      System.out.println(
          "DocumentMock: The word '" + word + "' appears " + counter + " times in the document");
      return document;
    }
  }

  static class DocumentTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private final String[][] document;
    private final int start;
    private final int end;
    private final String word;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    DocumentTask(String[][] document, int start, int end, String word) {
      super();
      this.document = document;
      this.start = start;
      this.end = end;
      this.word = word;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Override
    protected Integer compute() {
      int result = 0;
      if (end - start < MIN_LINES) result = processLines(document, start, end, word);
      else {
        int mid = (start + end) / 2;
        DocumentTask task1 = new DocumentTask(document, start, mid, word);
        DocumentTask task2 = new DocumentTask(document, mid, end, word);
        invokeAll(task1, task2);
        try {
          result = groupResults(task1.get(), task2.get());
        } catch (InterruptedException | ExecutionException e) {
          System.err.println(e);
        }
      }
      return result;
    }

    @SuppressWarnings("checkstyle:hiddenfield")
    private Integer processLines(String[][] doc, int start, int end, String word) {
      List<LineTask> tasks = new ArrayList<>();
      for (int i = start; i < end; i++) {
        LineTask task = new LineTask(doc[i], 0, doc[i].length, word);
        tasks.add(task);
      }
      invokeAll(tasks);
      int result = 0;
      for (LineTask task : tasks) {
        try {
          result = result + task.get();
        } catch (InterruptedException | ExecutionException e) {
          System.err.println(e);
        }
      }
      return Integer.valueOf(result);
    }

    private Integer groupResults(Integer number1, Integer number2) {
      return number1 + number2;
    }
  }

  static class LineTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private final String[] line;
    private final int start;
    private final int end;
    private final String word;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    LineTask(String[] line, int start, int end, String word) {
      super();
      this.line = line;
      this.start = start;
      this.end = end;
      this.word = word;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Override
    protected Integer compute() {
      Integer result = null;
      try {
        if (end - start < MIN_WORDS) {
          result = count(line, start, end, word);
        } else {
          int mid = (start + end) / 2;
          LineTask task1 = new LineTask(line, start, mid, word);
          LineTask task2 = new LineTask(line, mid, end, word);
          invokeAll(task1, task2);
          result = groupResults(task1.get(), task2.get());
        }
      } catch (InterruptedException | ExecutionException e) {
        System.err.println(e);
      }
      return result;
    }

    @SuppressWarnings({"checkstyle:hiddenfield", "PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
    private Integer count(String[] line, int start, int end, String word) {
      int counter = 0;
      for (int i = start; i < end; i++) {
        if (line[i].equals(word)) counter++;
      }
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      return counter;
    }

    private Integer groupResults(Integer number1, Integer number2) {
      return number1 + number2;
    }
  }
}
