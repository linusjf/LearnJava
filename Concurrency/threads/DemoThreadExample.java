package threads;

import java.io.PrintWriter;
import java.io.StringWriter;

// https://howtodoinjava-com.cdn.ampproject.org/v/s/howtodoinjava.com/java/multi-threading/how-to-restart-thread-using-uncaughtexceptionhandler/amp/?amp_js_v=a2&amp_gsa=1&usqp=mq331AQEKAFwAQ%3D%3D#aoh=15687659722173&referrer=https%3A%2F%2Fwww.google.com&amp_tf=From%20%251%24s&ampshare=https%3A%2F%2Fhowtodoinjava.com%2Fjava%2Fmulti-threading%2Fhow-to-restart-thread-using-uncaughtexceptionhandler%2F
@SuppressWarnings("PMD.AvoidUsingVolatile")
public enum DemoThreadExample {
  ;
  private static volatile int runCount;
  private static final int LIMIT = 5;

  public static void main(String[] args) {
    Task task = new Task();
    Thread thread = new Thread(task);
    thread.start();
  }

  static class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void uncaughtException(Thread t, Throwable e) {
      System.err.printf("An exception has been captured%n");
      System.err.printf("Thread: %s%n", t.getId());
      System.err.printf(
          "Exception: %s: %s%n", e.getClass().getName(), e.getMessage());
      System.err.printf("Stack Trace: %n");
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      System.err.printf("%s%n",
                        sw.toString().replace("%n", " ").replace("\t", " "));
      System.out.printf("Thread status: %s%n", t.getState());
      synchronized (DemoThreadExample.class) {
        DemoThreadExample.runCount++;
      }
      if (DemoThreadExample.runCount < LIMIT)
        new Thread(new Task()).start();
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Runnable {
    @Override
    public void run() {
      Thread.currentThread().setUncaughtExceptionHandler(
          new ExceptionHandler());
      System.out.println(Integer.parseInt("123"));
      System.out.println(Integer.parseInt("234"));
      System.out.println(Integer.parseInt("345"));
      System.out.println(Integer.parseInt("XYZ"));

      // This will cause NumberFormatException
      System.out.println(Integer.parseInt("456"));
    }
  }
}
