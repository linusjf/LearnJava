package com.javacodegeeks.patterns.facadepattern;

/**
 * Describe class <code>TestFacade</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.SystemPrintln")
public enum TestFacade {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final ScheduleServer scheduleServer = new ScheduleServer();
    final ScheduleServerFacade facadeServer =
        new ScheduleServerFacade(scheduleServer);
    facadeServer.startServer();
    System.out.println("Start working......");
    System.out.println("After work done.........");
    facadeServer.stopServer();
  }
}
