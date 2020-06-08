package threads;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Solver {
   final int N;
   final float[][] data;
   final CyclicBarrier barrier;

   public Solver(float[][] matrix) throws InterruptedException {
     data = matrix;
     N = matrix.length;
     Runnable barrierAction = 
       () -> mergeRows();
     barrier = new CyclicBarrier(N, barrierAction);

     List<Thread> threads = new ArrayList<>(N);
     for (int i = 0; i < N; i++) {
       Thread thread = new Thread(new Worker(i));
       threads.add(thread);
       thread.start();
     }

     // wait until done
     for (Thread thread : threads)
       thread.join();
   }

   void mergeRows() {
      System.out.println("Merging rows....");
   }
   
   class Worker implements Runnable {
     int myRow;
  
     Worker(int row) { 
       myRow = row; 
     }
     
     public void run() {
         processRow(myRow);

         try {
           barrier.await(1, TimeUnit.MINUTES);
         } catch (InterruptedException ex) {
           return;
         } catch (BrokenBarrierException ex) {
           return;
         } catch (TimeoutException ex) {
           return;
        }
     }

     void processRow(int row) {
      System.out.println("Processing row ..." 
          + row);
     }
   }
 }
