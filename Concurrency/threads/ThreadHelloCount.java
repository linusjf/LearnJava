package threads;

import java.util.Random;

public enum ThreadHelloCount {
  ;

  private static final Random RANDOM = new Random();

  public static void main(String[] args) {
    HelloThread hello = new HelloThread();
    CountThread count = new CountThread();
    hello.start();
    count.start();
  }

  static class HelloThread extends Thread {

    @Override
    public void run() {
      int pause;
      for (int i = 0; i < 5; i++) {
        try {
          System.out.println("Hello!");

          // Again, introduce an element
          // of randomness…
          pause = RANDOM.nextInt(3000);
          sleep(pause);
        } catch (InterruptedException interruptEx) {
          System.out.println(interruptEx);
          interrupt();
        }
      }
    }
  }

  static class CountThread extends Thread {
    int pause;

    @Override
    public void run() {
      for (int i = 0; i < 5; i++) {
        try {
          System.out.println(i);
          pause = RANDOM.nextInt(3000);
          sleep(pause);
        } catch (InterruptedException interruptEx) {
          System.out.println(interruptEx);
          interrupt();
        }
      }
    }
  }
}
