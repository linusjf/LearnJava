package javapuzzles;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Cretin {
  public static void main(String... args) throws InterruptedException {
    AtomicBoolean allowed = new AtomicBoolean(false);
    Thread[] threads = new Thread[1000];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
    Random random = ThreadLocalRandom.current(); 
         allowed.set(random.nextBoolean());
      });
      threads[i].start();
    }
    for (Thread thread : threads) 
      thread.join();
    System.out.println("Maurice is a cretin? " + allowed);
  }
}
