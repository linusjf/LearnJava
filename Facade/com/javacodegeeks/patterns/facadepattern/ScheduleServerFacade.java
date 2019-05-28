package com.javacodegeeks.patterns.facadepattern;

/**
 * Describe class <code>ScheduleServerFacade</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ScheduleServerFacade {
  /** Describe variable <code>scheduleServer</code> here. */
  private final ScheduleServer scheduleServer;

  /**
   * Creates a new <code>ScheduleServerFacade</code> instance.
   *
   * @param scheduleServer a <code>ScheduleServer</code> value
   */
  public ScheduleServerFacade(ScheduleServer scheduleServer) {
    this.scheduleServer = scheduleServer;
  }

  /** Describe <code>startServer</code> method here. */
  public void startServer() {
    scheduleServer.startBooting();
    scheduleServer.readSystemConfigFile();
    scheduleServer.init();
    scheduleServer.initializeContext();
    scheduleServer.initializeListeners();
    scheduleServer.createSystemObjects();
  }

  /** Describe <code>stopServer</code> method here. */
  public void stopServer() {
    scheduleServer.releaseProcesses();
    scheduleServer.destroy();
    scheduleServer.destroySystemObjects();
    scheduleServer.destroyListeners();
    scheduleServer.destroyContext();
    scheduleServer.shutdown();
  }
}
