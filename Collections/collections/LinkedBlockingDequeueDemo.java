package collections;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public enum LinkedBlockingDequeueDemo {
  ;

  public static void main(String[] args) {
    try {
      LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);
      Client client = new Client(list);
      Thread thread = new Thread(client);
      thread.start();
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 3; j++) {
          String request = list.take();
          System.out.printf(
              "Main: Request: %s at %s. Size: %d%n", request, new Date(), list.size());
        }
        MILLISECONDS.sleep(300);
      }
      System.out.printf("Main: End of the program.%n");
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }

  static class Client implements Runnable {
    private final LinkedBlockingDeque<String> requestList;

    Client(LinkedBlockingDeque<String> requestList) {
      this.requestList = requestList;
    }

    @Override
    public void run() {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 5; j++) {
          StringBuilder request = new StringBuilder();
          request.append(i).append(':').append(j);
          try {
            requestList.put(request.toString());
          } catch (InterruptedException e) {
            System.err.println(e);
          }
          System.out.printf("Client: %s at %s.%n", request, new Date());
        }
        try {
          SECONDS.sleep(2);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
      System.out.printf("Client: End.%n");
    }
  }
}
