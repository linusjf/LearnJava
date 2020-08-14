package performance;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 
 Time the execution of any block of code.
 
 <P>This implementation times the duration using <code>System.nanoTime</code>.
 
 <P>On most systems <code>System.currentTimeMillis</code> has a time 
 resolution of about 10ms, which is quite poor for timing code, so it is
 avoided here.
*/
public final class Stopwatch {

  /**
   An example of the use of this class to
   time the execution of simple String manipulation code.
  */
  public static void main(String... arguments) {
    Stopwatch stopwatch = new Stopwatch();

    stopwatch.start();

    //do stuff
    StringBuilder messageOne = new StringBuilder();
    int numIterations = 5000;
    for(int idx=0; idx < numIterations; ++idx){
      messageOne.append("blah");
    }

    stopwatch.stop();
    //Note that there is no need to call a method to get the duration,
    //since toString is automatic here
    log("The reading for StringBuilder is: " + stopwatch);

    //reuse the same stopwatch to measure an alternative implementation
    //Note that there is no need to call a reset method.
    stopwatch.start();

    //do stuff again
    String messageTwo = null;
    for(int idx=0; idx < numIterations; ++idx){
      messageTwo = messageTwo + "blah";
    }

    stopwatch.stop();
    //perform a numeric comparsion
    if ( stopwatch.toValue() > 5 ) {
      log("The reading is high: " + stopwatch);
    }
    else {
      log("The reading is low: " + stopwatch);
    }
  }
  
  /**
   Start the stopwatch.
   @throws IllegalStateException if the stopwatch is already running.
  */
  public void start(){
    if (isRunning) {
      throw new IllegalStateException("Must stop before calling start again.");
    }
    //reset both start and stop
    start = System.nanoTime();
    stop = 0;
    isRunning = true;
    hasBeenUsedOnce = true;
  }

  /**
   Stop the stopwatch.
   @throws IllegalStateException if the stopwatch is not already running.
  */
  public void stop() {
    if (!isRunning) {
      throw new IllegalStateException("Cannot stop if not currently running.");
    }
    stop = System.nanoTime();
    isRunning = false;
  }

  /**
   Express the "reading" on the stopwatch.
    
   <P>Example: <code>123.456 ms</code>. The resolution of timings on most systems 
   is on the order of a few microseconds, so this style of presentation is usually 
   appropriate for reflecting the real precision of most timers. 
  
   <P>Ref: https://blogs.oracle.com/dholmes/entry/inside_the_hotspot_vm_clocks
     
   @throws IllegalStateException if the Stopwatch has never been used,
   or if the stopwatch is still running.
  */
  @Override public String toString() {
    validateIsReadable();
    StringBuilder result = new StringBuilder();
    BigDecimal value = new BigDecimal(toValue());//scale is zero
    //millis, with 3 decimals:
    value = value.divide(MILLION, 3, RoundingMode.HALF_EVEN);
    result.append(value);
    result.append(" ms");
    return result.toString();
  }

  /**
   Express the "reading" on the stopwatch as a numeric type, in nanoseconds.
  
   @throws IllegalStateException if the Stopwatch has never been used,
   or if the stopwatch is still running.
  */
  public long toValue() {
    validateIsReadable();
    return  stop - start;
  }
  
  // PRIVATE
  private long start;
  private long stop;

  private boolean isRunning;
  private boolean hasBeenUsedOnce;
  
  /** Converts from nanos to millis. */
  private static final BigDecimal MILLION = new BigDecimal("1000000");
  
  /**
   Throws IllegalStateException if the watch has never been started,
   or if the watch is still running.
  */
  private void validateIsReadable() {
    if (isRunning) {
      String message = "Cannot read a stopwatch which is still running.";
      throw new IllegalStateException(message);
    }
    if (!hasBeenUsedOnce) {
      String message = "Cannot read a stopwatch which has never been started.";
      throw new IllegalStateException(message);
    }
  }
  
  private static void log(String msg) {
    System.out.println(msg);
  }
} 
