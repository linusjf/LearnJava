package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public enum CompletionServiceDemo {
  ;

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    CompletionService<String> service = new ExecutorCompletionService<>(executor);
    ReportRequest faceRequest = new ReportRequest("Face", service);
    ReportRequest onlineRequest = new ReportRequest("Online", service);
    ReportProcessor processor = new ReportProcessor(service);
    System.out.printf("Main: Creating and starting the Threads\n");
    Thread faceThread = new Thread(faceRequest);
    Thread onlineThread = new Thread(onlineRequest);
    Thread senderThread = new Thread(processor);
    faceThread.start();
    onlineThread.start();
    senderThread.start();
    try {
      System.out.printf("Main: Waiting for the report generators.\n");
      faceThread.join();
      onlineThread.join();
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    System.out.printf("Main: Shutting down the executor.\n");
    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
    processor.setEnd(true);
    System.out.println("Main: Ends");
  }

  static class ReportGenerator implements Callable<String> {
    private final String sender;
    private final String title;

    ReportGenerator(String sender, String title) {
      this.sender = sender;
      this.title = title;
    }

    @Override
    public String call() throws Exception {
      try {
        Long duration = (long) (Math.random() * 10);
        System.out.printf(
            "%s_%s: ReportGenerator: Generating a report utilizing %d seconds\n",
            this.sender, this.title, duration);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
      return sender + ": " + title;
    }
  }

  static class ReportRequest implements Runnable {
    private final String name;
    private final CompletionService<String> service;

    ReportRequest(String name, CompletionService<String> service) {
      this.name = name;
      this.service = service;
    }

    @Override
    public void run() {
      ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
      service.submit(reportGenerator);
    }
  }

  static class ReportProcessor implements Runnable {
    private final CompletionService<String> service;
    private boolean end;

    ReportProcessor(CompletionService<String> service) {
      this.service = service;
      end = false;
    }

    @Override
    public void run() {
      while (!end) {
        try {
          Future<String> result = service.poll(20, TimeUnit.SECONDS);
          if (result != null) {
            String report = result.get();
            System.out.printf("ReportReceiver: Report Received: %s\n", report);
          }
        } catch (InterruptedException | ExecutionException e) {
          System.err.println(e);
        }
      }
      System.out.printf("ReportSender: End\n");
    }

    public void setEnd(boolean end) {
      this.end = end;
    }
  }
}
