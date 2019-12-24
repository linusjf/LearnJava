package javapuzzles;

import static java.lang.Thread.*;

@SuppressWarnings("PMD.LawOfDemeter")
public enum SelfInterruption {
  ;

  public static void main(String[] args) {
    currentThread().interrupt();
    if (interrupted()) System.out.println("Interrupted: " + interrupted());
    else System.out.println("Not interrupted: " + interrupted());
    altMain(args);
  }

  public static void altMain(String... args) {
    currentThread().interrupt();
    if (currentThread().isInterrupted())
      System.out.println("Interrupted: " + currentThread().isInterrupted());
    else System.out.println("Not interrupted: " + currentThread().isInterrupted());
  }
}
