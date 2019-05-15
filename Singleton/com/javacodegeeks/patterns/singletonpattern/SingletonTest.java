package com.javacodegeeks.patterns.singletonpattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
public class SingletonTest {

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    try {
      testConcurrency();
      testSerializable();
      testCloneable();
      testReflection();
      testState();
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }

  private static void testConcurrency() throws Throwable {

    final int size = 12;

    final CyclicBarrier cyclicBarrier = new CyclicBarrier(size);

    final AtomicReference<Throwable> exception = new AtomicReference<>();

    // final Set<Long> generatedValues = new HashSet<>(size);
    final Set<Long> generatedValues = new LinkedHashSet<>(size);
    final Set<Singleton> instances =
        Collections.newSetFromMap(new IdentityHashMap<Singleton, Boolean>());

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
        //      assert false : "Expected one instance, but found none";
        throw new AssertionError("Expected one instance, but found none");

      case 1:
        System.out.println("Only one instance created and available.");
        break;
      default:
        //     assert false : "Expected one instance, but found many";
        throw new AssertionError("Expected one instance, but found many");
    }
    System.out.println("Sequence in order in which inserted: ");
    for (final long value : generatedValues) 
      System.out.print(value + " ");
    System.out.println();
  }

  private static void testSerializable() throws Throwable {
    Singleton instance = Singleton.getInstance();

    ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
    out.writeObject(instance);
    out.close();

    // deserialize from file to object
    ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.ser"));
    Singleton instance2 = (Singleton) in.readObject();

    in.close();
    System.out.println("instance hashCode:- " + instance.hashCode());
    System.out.println("instance2 hashCode:- " + instance2.hashCode());
  }

  private static void testCloneable() {
    Singleton obj = Singleton.getInstance();
    try {
      obj.clone();
    } catch (CloneNotSupportedException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void testReflection() {
    try {
      Constructor<?>[] constructors = Singleton.class.getDeclaredConstructors();
      for (Constructor<?> constructor : constructors) {
        constructor.setAccessible(true);
        Singleton obj = (Singleton) constructor.newInstance();
        System.out.println("obj: Break through Reflection:" + obj);
      }
    } catch (Exception ew) {
      System.out.println(ew.getCause().getMessage());
    }
  }

  private static void testState() throws Throwable {
    resetSingleton();
    Singleton singleton = Singleton.getInstance();
    if (singleton.getNextValue() != 0L)
      throw new AssertionError("Next value should be zero.");
    resetSingleton();
    singleton = Singleton.getInstance();
    singleton.getNextValue();
    singleton.getNextValue();
    singleton.getNextValue();
    if (singleton.getNextValue() != 3L)
      throw new AssertionError("Next value should be three.");
    System.out.println("No assert errors. State validated.");
  }

  private static void resetSingleton()
      throws SecurityException,
           NoSuchFieldException,
           IllegalArgumentException,
           IllegalAccessException {
    Field instance = Singleton.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
  }
}
