package threads;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public enum Main {
  ;

  public static void main(String[] args) {
    Thread[] threads = new Thread[10];

    for (int i = 0; i < 10; i++) {
      threads[i] = new Thread(new Calculator(i));
      threads[i].setPriority(i % 2 == 0 ? Thread.MAX_PRIORITY
                                        : Thread.MIN_PRIORITY);
      threads[i].setName("Thread " + i);
    }

    Thread.State[] status = new Thread.State[10];
    try (BufferedWriter file = Files.newBufferedWriter(Paths.get("./log.txt"));
         PrintWriter pw = new PrintWriter(file);) {
      for (int i = 0; i < 10; i++) {
        pw.println("Main : Status of Thread " + i + " : "
                   + threads[i].getState());
        status[i] = threads[i].getState();
      }

      for (Thread t: threads)
        t.start();
      logThreadState(threads, status, pw);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }

  private static void logThreadState(Thread[] threads,
                                     Thread.State[] status,
                                     PrintWriter pw) {
    boolean finish = false;
    while (!finish) {
      for (int i = 0; i < 10; i++) {
        if (threads[i].getState() != status[i]) {
          writeThreadInfo(pw, threads[i], status[i]);
          status[i] = threads[i].getState();
        }
      }
      finish = true;
      for (int i = 0; i < 10; i++) {
        finish = finish && threads[i].getState() == Thread.State.TERMINATED;
      }
    }
  }

  private static void writeThreadInfo(PrintWriter pw,
                                      Thread thread,
                                      Thread.State state) {
    pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
    pw.printf("Main : Priority: %d\n", thread.getPriority());
    pw.printf("Main : Old State: %s\n", state);
    pw.printf("Main : New State: %s\n", thread.getState());
    pw.printf("Main : ************************************\n");
  }
}
