package performance;

import java.util.Arrays;
import java.util.Date;

public final class ArrayCopier {

  public static void main (String... args) {
    String action = args[0];
    int numIterations = 0;
    if (args.length == 2) {
      numIterations = Integer.parseInt(args[1]);
    }

    if ("performance".equals(action)) {
      demoPerformance(numIterations);
    }
    else if ("storage".equals(action)) {
      demoIndependanceOfStorage();
    }
  }

  /**
  * Display the time it takes to copy an array in various ways.
  */
  private static void demoPerformance(int numIterations){
    Stopwatch stopwatch = new Stopwatch();
    int[] numbers = {1,2,3,4,5,6,7,8,9,10};

    stopwatch.start();
    copyUsingClone(numbers, numIterations);
    stopwatch.stop();
    log("Using clone: " + stopwatch);

    stopwatch.start();
    copyUsingArraycopy(numbers, numIterations);
    stopwatch.stop();
    log("Using System.arraycopy: " + stopwatch);

    stopwatch.start();
    copyUsingArraysCopyOf(numbers, numIterations);
    stopwatch.stop();
    log("Using Arrays.copyOf: " + stopwatch);

    stopwatch.start();
    copyUsingForLoop(numbers, numIterations);
    stopwatch.stop();
    log("Using for loop: " + stopwatch);
  }

  private static void copyUsingClone(int[] array , int numIterations) {
    for(int idx = 0 ; idx < numIterations; ++idx) {

      int[] copy = array.clone();

    }
  }

  private static void copyUsingArraycopy(int[] array , int numIterations) {
    for(int idx = 0 ; idx < numIterations; ++idx) {

      int [] copy = new int[array.length];
      System.arraycopy( array, 0, copy, 0, array.length );

    }
  }

  private static void copyUsingArraysCopyOf(int[] array , int numIterations) {
    for(int idx = 0 ; idx < numIterations; ++idx) {

      int[] copy = Arrays.copyOf(array, array.length);

    }
  }


  private static void copyUsingForLoop(int[] array , int numIterations) {
    for(int iterIdx = 0 ; iterIdx < numIterations; ++iterIdx) {

      int [] copy = new int[array.length];
      for (int idx = 0; idx < array.length; ++idx) {
        copy[idx] = array[idx];
      }

    }
  }

  private static void log(String message){
    System.out.println(message);  
  }
  
  /**
  * (The for-loop and System.arraycopy styles clearly have independent
  * storage, and are not exercised in this method.)
  */
  private static void demoIndependanceOfStorage() {
    //a clone of a one-dimensional array has independent storage
    int[] numbers = {1,1,1,1};
    int[] numbersClone = numbers.clone();
    //set 0th element to 0, and compare
    numbersClone[0] = 0;
    log("Altered clone has NOT affected original:");
    log("numbersClone[0]: " + numbersClone[0]);
    log("numbers[0]: " +  numbers[0]);

    //the clone of a multi-dimensional array does *not* have
    //independant storage
    int[][] matrix = { {1,1}, {1,1} };
    int[][] matrixClone = matrix.clone();
    //set 0-0th element to 0, and compare
    matrixClone[0][0] = 0;
    log("Altered clone has affected original:");
    log("matrixClone element 0-0:" + matrixClone[0][0]);
    log("matrix element 0-0: " + matrix[0][0]);

    //the clone of an array of objects as well is only shallow
    Date[] dates = {new Date()};
    log("Original date: " + dates[0]);
    Date[] datesClone = dates.clone();
    datesClone[0].setTime(0);
    log("Altered clone has affected original:");
    log("datesClone[0]:" + datesClone[0]);
    log("dates[0]: " + dates[0]);
  }
} 
