package com.javacodegeeks.patterns.singletonpattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Describe class <code>SingletonTest</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum SingletonTest {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    testConcurrency();
    testSerializable();
    testCloneable();
    testReflection();
    testState();
  }

  @SuppressWarnings("checkstyle:IllegalCatch")
  private static void testConcurrency() {

    final int size = 12;

    final CyclicBarrier cyclicBarrier = new CyclicBarrier(size);

    final AtomicReference<Throwable> exception = new AtomicReference<>();

    // final Set<Long> generatedValues = new HashSet<>(size);
    final Set<Long> generatedValues = new LinkedHashSet<>(size);
    final Set<Singleton> instances =
        Collections.newSetFromMap(new IdentityHashMap<Singleton, Boolean>());

    final List<Thread> threads = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      final Thread thread =
          new Thread(
            new Runnable() {
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
    try {
      for (final Thread thread : threads) thread.join();

      if (exception.get() != null) throw exception.get();

      switch (instances.size()) {
        case 0:
          throw new AssertionError("Expected one instance, but found none");

        case 1:
          System.out.println("Only one instance created and available.");
          break;
        default:
          throw new AssertionError("Expected one instance, but found many");
      }
      System.out.println("Sequence in order in which inserted: ");
      for (final long value : generatedValues) System.out.print(value + " ");
      System.out.println();
    } catch (Throwable throwable) { // NOPMD
      System.out.println(throwable.getMessage());
    }
  }

  private static void testSerializable() {
    final Singleton instance = Singleton.getInstance();

    try {
      final ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
      out.writeObject(instance);
      out.close();

      // deserialize from file to object
      final ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.ser"));
      final Singleton instance2 = (Singleton) in.readObject();
      in.close();
      System.out.println("instance hashCode:- " + instance.hashCode());
      System.out.println("instance2 hashCode:- " + instance2.hashCode());
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void testCloneable() {
    final Singleton obj = Singleton.getInstance();
    try {
      obj.clone();
    } catch (CloneNotSupportedException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void testReflection() {
    try {
      final Constructor<?>[] constructors = Singleton.class.getDeclaredConstructors();
      for (Constructor<?> constructor : constructors) {
        constructor.setAccessible(true);
        final Singleton obj = (Singleton) constructor.newInstance();
        System.out.println("obj: Break through Reflection:" + obj);
      }
    } catch (SecurityException
        | InstantiationException
        | IllegalArgumentException
        | IllegalAccessException
        | InvocationTargetException e) {
      System.out.println(e.getCause().getMessage());
    }
  }

  private static void testState() {
    try {
      resetSingleton();
      Singleton singleton = Singleton.getInstance();
      if (singleton.getNextValue() != 0L) throw new AssertionError("Next value should be zero.");
      resetSingleton();
      singleton = Singleton.getInstance();
      @SuppressWarnings("checkstyle:magicnumber")
      final long expectedValue = 3L;
      singleton.getNextValue();
      singleton.getNextValue();
      singleton.getNextValue();
      if (singleton.getNextValue() != expectedValue)
        throw new AssertionError("Next value should be three.");
      System.out.println("No assert errors. State validated.");
    } catch (NoSuchFieldException | IllegalAccessException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void resetSingleton()
      throws SecurityException, NoSuchFieldException, IllegalArgumentException,
                      IllegalAccessException {
    final Field instance = Singleton.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
  }
}
