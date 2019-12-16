package threads;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public enum CustomPhaserDemo {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    MyPhaser phaser = new MyPhaser();
    Student[] students = new Student[5];
    for (int i = 0; i < students.length; i++) {
      students[i] = new Student(phaser);
      phaser.register();
    }
    Thread[] threads = new Thread[students.length];
    for (int i = 0; i < students.length; i++) {
      threads[i] = new Thread(students[i], "Student " + i);
      threads[i].start();
    }
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("Main:  The phaser has finished: %s.%n", phaser.isTerminated());
  }

  static class MyPhaser extends Phaser {
    @SuppressWarnings("checkstyle:returncount")
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
      switch (phase) {
        case 0:
          return studentsArrived();
        case 1:
          return finishFirstExercise();
        case 2:
          return finishSecondExercise();
        case 3:
          return finishExam();
        default:
          return true;
      }
    }

    private boolean studentsArrived() {
      System.out.printf("Phaser: Exams are starting. Students are ready.%n");
      System.out.printf("Phaser: We have %d students.%n", getRegisteredParties());
      return false;
    }

    private boolean finishFirstExercise() {
      System.out.printf("Phaser: All students have finished the first exercise.%n");
      System.out.printf("Phaser: Time for the second one.%n");
      return false;
    }

    private boolean finishSecondExercise() {
      System.out.printf("Phaser: All students have finished the second exercise.%n");
      System.out.printf("Phaser: Time for the third one.%n");
      return false;
    }

    private boolean finishExam() {
      System.out.printf("Phaser: All students have finished the exam.%n");
      System.out.printf("Phaser: Thank you for your time.%n");
      return true;
    }
  }

  static class Student implements Runnable {
    private final Phaser phaser;

    Student(Phaser phaser) {
      this.phaser = phaser;
    }

    @Override
    public void run() {
      System.out.printf(
          "%s: Has arrived to take the exam. %s%n", Thread.currentThread().getName(), new Date());
      phaser.arriveAndAwaitAdvance();
      System.out.printf("%s: Is about to start the first exercise. %s%n",
          Thread.currentThread().getName(), new Date());
      doExercise1();
      System.out.printf("%s: Has completed the first exercise. %s%n",
          Thread.currentThread().getName(), new Date());
      phaser.arriveAndAwaitAdvance();
      System.out.printf(
          "%s: Is starting the second exercise.%s%n", Thread.currentThread().getName(), new Date());
      doExercise2();
      System.out.printf("%s: Has completed the second exercise. %s%n",
          Thread.currentThread().getName(), new Date());
      phaser.arriveAndAwaitAdvance();
      System.out.printf(
          "%s: Is starting the third exercise. %s%n", Thread.currentThread().getName(), new Date());
      doExercise3();
      System.out.printf(
          "%s: Finished the exam. %s%n", Thread.currentThread().getName(), new Date());
      phaser.arriveAndAwaitAdvance();
    }

    private void doExercise1() {
      try {
        long duration = (long) (Math.random() * 10);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }

    private void doExercise2() {
      try {
        long duration = (long) (Math.random() * 10);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }

    private void doExercise3() {
      try {
        long duration = (long) (Math.random() * 10);
        TimeUnit.SECONDS.sleep(duration);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
  }
}
