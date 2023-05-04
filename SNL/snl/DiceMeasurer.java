package snl;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.atomic.LongAccumulator;

public class DiceMeasurer {
  private static final int RUNS = 600_000_000;
  private static final LongAccumulator best =
      new LongAccumulator(Long::min, Long.MAX_VALUE);

  public static void main(String... args) {
    testFair();
    testFairSR();
    testFast();
    testFastFixedMode();
    testVeryFast();
  }

  @SuppressWarnings("CPD-START")
  private static void testFairSR() {
    for (int j = 0; j < 20; j++) {
      long time = System.nanoTime();
      try {
        //      Dice dice = new DiceFair();  // 1433 ms
        Dice dice = new DiceFair(new SecureRandom());  // 85495 ms
        // Dice dice = new DiceFast(); // 1001 ms
        // Dice dice = new DiceFastFixedMod();  // 690 ms
        // Dice dice = new DiceVeryFast(); // 372 ms
        int[] results = new int[6];
        for (int i = 0; i < RUNS; i++) {
          results[dice.roll() - 1]++;
        }
        System.out.println("Fair results = " + Arrays.toString(results));
        int[] diffs = new int[6];
        for (int i = 0; i < diffs.length; i++) {
          diffs[i] = results[i] - RUNS / 6;
        }
        System.out.println("test fair diffs = " + Arrays.toString(diffs));
      } finally {
        time = System.nanoTime() - time;
        best.accumulate(time);
        System.out.printf("test fair time = %dms%n", (time / 1_000_000));
      }
    }
    System.out.printf("best fair time = %dms%n",
                      best.getThenReset() / 1_000_000);
  }

  private static void testFair() {
    for (int j = 0; j < 20; j++) {
      long time = System.nanoTime();
      try {
        Dice dice = new DiceFair();  // 1433 ms
        // Dice dice = new DiceFair(new SecureRandom()); // 85495 ms
        // Dice dice = new DiceFast(); // 1001 ms
        // Dice dice = new DiceFastFixedMod();  // 690 ms
        // Dice dice = new DiceVeryFast(); // 372 ms
        int[] results = new int[6];
        for (int i = 0; i < RUNS; i++) {
          results[dice.roll() - 1]++;
        }
        System.out.println("Fair results = " + Arrays.toString(results));
        int[] diffs = new int[6];
        for (int i = 0; i < diffs.length; i++) {
          diffs[i] = results[i] - RUNS / 6;
        }
        System.out.println("test fair diffs = " + Arrays.toString(diffs));
      } finally {
        time = System.nanoTime() - time;
        best.accumulate(time);
        System.out.printf("test fair time = %dms%n", (time / 1_000_000));
      }
    }
    System.out.printf("best fair time = %dms%n",
                      best.getThenReset() / 1_000_000);
  }

  @SuppressWarnings("CPD-END")
  private static void testFast() {
    for (int j = 0; j < 20; j++) {
      long time = System.nanoTime();
      try {
        // Dice dice = new DiceFair(); // 1433 ms
        // Dice dice = new DiceFair(new SecureRandom()); // 85495 ms
        Dice dice = new DiceFast();  // 1001 ms
        // Dice dice = new DiceFastFixedMod();  // 690 ms
        // Dice dice = new DiceVeryFast(); // 372 ms
        int[] results = new int[6];
        for (int i = 0; i < RUNS; i++) {
          results[dice.roll() - 1]++;
        }
        System.out.println("Fast results = " + Arrays.toString(results));
        int[] diffs = new int[6];
        for (int i = 0; i < diffs.length; i++) {
          diffs[i] = results[i] - RUNS / 6;
        }
        System.out.println("test fast diffs = " + Arrays.toString(diffs));
      } finally {
        time = System.nanoTime() - time;
        best.accumulate(time);
        System.out.printf("test fast time = %dms%n", (time / 1_000_000));
      }
    }
    System.out.printf("best fast time = %dms%n",
                      best.getThenReset() / 1_000_000);
  }

  private static void testVeryFast() {
    for (int j = 0; j < 20; j++) {
      long time = System.nanoTime();
      try {
        // Dice dice = new DiceFair(); // 1433 ms
        // Dice dice = new DiceFair(new SecureRandom()); // 85495 ms
        //        Dice dice = new DiceFast(); // 1001 ms
        //  Dice dice = new DiceFastFixedMod();  // 690 ms
        Dice dice = new DiceVeryFast();  // 372 ms
        int[] results = new int[6];
        for (int i = 0; i < RUNS; i++) {
          results[dice.roll() - 1]++;
        }
        System.out.println("Very Fast results = " + Arrays.toString(results));
        int[] diffs = new int[6];
        for (int i = 0; i < diffs.length; i++) {
          diffs[i] = results[i] - RUNS / 6;
        }
        System.out.println("test very fast diffs = " + Arrays.toString(diffs));
      } finally {
        time = System.nanoTime() - time;
        best.accumulate(time);
        System.out.printf("test very fast time = %dms%n", (time / 1_000_000));
      }
    }
    System.out.printf("best very fast time = %dms%n",
                      best.getThenReset() / 1_000_000);
  }

  private static void testFastFixedMode() {
    for (int j = 0; j < 20; j++) {
      long time = System.nanoTime();
      try {
        // Dice dice = new DiceFair(); // 1433 ms
        // Dice dice = new DiceFair(new SecureRandom()); // 85495 ms
        // Dice dice = new DiceFast(); // 1001 ms
        Dice dice = new DiceFastFixedMod();  // 690 ms
        // Dice dice = new DiceVeryFast(); // 372 ms
        int[] results = new int[6];
        for (int i = 0; i < RUNS; i++) {
          results[dice.roll() - 1]++;
        }
        System.out.println("Fast fixed mode results = "
                           + Arrays.toString(results));
        int[] diffs = new int[6];
        for (int i = 0; i < diffs.length; i++) {
          diffs[i] = results[i] - RUNS / 6;
        }
        System.out.println("test fast fixed mode diffs = "
                           + Arrays.toString(diffs));
      } finally {
        time = System.nanoTime() - time;
        best.accumulate(time);
        System.out.printf("test fast mixed mode time = %dms%n",
                          (time / 1_000_000));
      }
    }
    System.out.printf("best fast mixed mode time = %dms%n",
                      best.getThenReset() / 1_000_000);
  }
}
