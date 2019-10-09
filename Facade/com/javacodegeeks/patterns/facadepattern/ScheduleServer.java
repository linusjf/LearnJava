package com.javacodegeeks.patterns.facadepattern;

/**
 * Describe class <code>ScheduleServer</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public class ScheduleServer {

  /** Describe <code>startBooting</code> method here. */
  public void startBooting() {
    System.out.println("Starts booting...");
  }

  /** Describe <code>readSystemConfigFile</code> method here. */
  public void readSystemConfigFile() {
    System.out.println("Reading system config files...");
  }

  /** Describe <code>init</code> method here. */
  public void init() {
    System.out.println("Initializing...");
  }

  /** Describe <code>initializeContext</code> method here. */
  public void initializeContext() {
    System.out.println("Initializing context...");
  }

  /** Describe <code>initializeListeners</code> method here. */
  public void initializeListeners() {
    System.out.println("Initializing listeners...");
  }

  /** Describe <code>createSystemObjects</code> method here. */
  public void createSystemObjects() {
    System.out.println("Creating system objects...");
  }

  /** Describe <code>releaseProcesses</code> method here. */
  public void releaseProcesses() {
    System.out.println("Releasing processes...");
  }

  /** Describe <code>destroy</code> method here. */
  public void destroy() {
    System.out.println("Destroying...");
  }

  /** Describe <code>destroySystemObjects</code> method here. */
  public void destroySystemObjects() {
    System.out.println("Destroying system objects...");
  }

  /** Describe <code>destroyListeners</code> method here. */
  public void destroyListeners() {
    System.out.println("Destroying listeners...");
  }

  /** Describe <code>destroyContext</code> method here. */
  public void destroyContext() {
    System.out.println("Destroying context...");
  }

  /** Describe <code>shutdown</code> method here. */
  public void shutdown() {
    System.out.println("Shutting down...");
  }
}
