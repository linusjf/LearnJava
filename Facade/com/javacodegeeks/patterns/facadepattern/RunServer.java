package com.javacodegeeks.patterns.facadepattern;

/**
 * Describe class <code>RunServer</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum RunServer {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final ScheduleServer scheduleServer = new ScheduleServer();

    scheduleServer.startBooting();
    scheduleServer.readSystemConfigFile();
    scheduleServer.init();
    scheduleServer.initializeContext();
    scheduleServer.initializeListeners();
    scheduleServer.createSystemObjects();

    System.out.println("Start working......");
    System.out.println("After work done.........");

    scheduleServer.releaseProcesses();
    scheduleServer.destroy();
    scheduleServer.destroySystemObjects();
    scheduleServer.destroyListeners();
    scheduleServer.destroyContext();
    scheduleServer.shutdown();
  }
}
