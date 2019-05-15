package com.javacodegeeks.patterns.singletonpattern;

import java.lang.AssertionError;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

public class SingletonTest {

  public static void main(String[] args)  {
 try  {   
    testConcurrency();
 }
 catch (Throwable t)
 {
   t.printStackTrace();
 }
  }
  
  public static void testConcurrency() throws Throwable {

    final int size = 12;

    final CyclicBarrier cyclicBarrier = new CyclicBarrier(size);

    final AtomicReference<Throwable> exception = new AtomicReference<>();

    final Set<Long> generatedValues = new HashSet<>(size);

    final Set<Singleton> instances = Collections
      .newSetFromMap(new IdentityHashMap<Singleton, Boolean>());

    final List<Thread> threads = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      final Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            cyclicBarrier.await();
          } catch (InterruptedException | BrokenBarrierException e) {
            exception.compareAndSet(null, e);
            return;
          }

          final Singleton singleton = Singleton.getInstance();
          final long value = singleton.getNextValue();

          // Synchronise the access as the collections used are not thread-safe
          synchronized (SingletonTest.class) {
            if (!generatedValues.add(value)) {
              exception.compareAndSet(null, new AssertionError("Duplicate value " + value));
              return;
            }
            instances.add(singleton);
          }
        }
      });
      thread.start();
      threads.add(thread);
    }

    for (final Thread thread : threads) {
      thread.join();
    }

    if (exception.get() != null) {
      throw exception.get();
    }

    switch (instances.size()) {
      case 0:
        assert false : "Expected one instance, but found none";
        break;
      case 1:
        System.out.println("Only one instance created and available.");
        break;
      default:
        assert false : "Expected one instance, but found many";
    }
  }


}
