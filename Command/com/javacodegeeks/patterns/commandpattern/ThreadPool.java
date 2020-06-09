package com.javacodegeeks.patterns.commandpattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.stream.Stream;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class ThreadPool {
  private static final Logger LOGGER = Logger.getLogger(ThreadPool.class.getName());
  private final BlockingQueue<Job> jobQueue;
  private final Thread[] jobThreads;
  private final AtomicBoolean shutdown;

  public ThreadPool(int n) {
    jobQueue = new LinkedBlockingQueue<>();
    jobThreads = new Thread[n];
    shutdown = new AtomicBoolean();
    for (int i = 0; i < n; i++) {
      jobThreads[i] = new Worker("Pool Thread " + i);
      jobThreads[i].start();
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
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
        LOGGER.severe(e.getMessage());
      }
    }
    shutdown.set(true);
    for (Thread workerThread : jobThreads) workerThread.interrupt();
  }


  private class Worker extends Thread {
    Worker(String name) {
      super(name);
    }

    @Override
    public void run() {
      while (!shutdown.get()) {
        try {
          runTopJob();
        } catch (InterruptedException e) {
          LOGGER.severe(e.getMessage());
        }
      }
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    private void runTopJob() throws InterruptedException {
      Stream.generate(() -> {
        try {
          return jobQueue.take();
        } catch (InterruptedException ie) {
          return null;
        }
      }).findFirst().get().run();
    }
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "ThreadPool(jobQueue=" + this.jobQueue + ", jobThreads=" + java.util.Arrays.deepToString(this.jobThreads) + ", shutdown=" + this.shutdown + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof ThreadPool)) return false;
    ThreadPool other = (ThreadPool) o;
    Object this$jobQueue = this.jobQueue;
    Object other$jobQueue = other.jobQueue;
    if (this$jobQueue == null ? other$jobQueue != null : !this$jobQueue.equals(other$jobQueue)) return false;
    if (!java.util.Arrays.deepEquals(this.jobThreads, other.jobThreads)) return false;
    Object this$shutdown = this.shutdown;
    Object other$shutdown = other.shutdown;
    if (this$shutdown == null ? other$shutdown != null : !this$shutdown.equals(other$shutdown)) return false;
    return true;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $jobQueue = this.jobQueue;
    result = result * PRIME + ($jobQueue == null ? 43 : $jobQueue.hashCode());
    result = result * PRIME + java.util.Arrays.deepHashCode(this.jobThreads);
    Object $shutdown = this.shutdown;
    result = result * PRIME + ($shutdown == null ? 43 : $shutdown.hashCode());
    return result;
  }
}
