package com.javacodegeeks.patterns.commandpattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool {
  private final BlockingQueue<Job> jobQueue;
  private final Thread[] jobThreads;
  private AtomicBoolean shutdown;

  public ThreadPool(int n) {
    jobQueue = new LinkedBlockingQueue<>();
    jobThreads = new Thread[n];
    shutdown = new AtomicBoolean();
    for (int i = 0; i < n; i++) {
      jobThreads[i] = new Worker("Pool Thread " + i);
      jobThreads[i].start();
    }
  }

  public void addJob(Job r) {
    try {
      jobQueue.put(r);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void shutdownPool() {
    while (!jobQueue.isEmpty()) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.err.println(e.getMessage());
      }
    }
    shutdown.set(true);
    for (Thread workerThread : jobThreads) {
      workerThread.interrupt();
    }
  }

  private class Worker extends Thread {

    Worker(String name) {
      super(name);
    }

    @Override
    public void run() {
      while (!shutdown.get()) {
        try {
          Job r = jobQueue.take();
          r.run();
        } catch (InterruptedException e) {
          System.err.println(e.getMessage());
        }
      }
    }
  }
}
