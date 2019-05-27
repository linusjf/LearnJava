package com.javacodegeeks.niotutorial.server;

/**
 * Describe class <code>Constants</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Constants { //NOPMD

  /**
   * Describe constant <code>CLIENT_BYTE_BUFFER_CAPACITY</code> here.
   *
   */
  public static final int CLIENT_BYTE_BUFFER_CAPACITY = 1024;
  
  /**
   * Describe constant <code>HOST</code> here.
   *
   */
  public static final String HOST = "localhost";
  
  /**
   * Describe constant <code>PORT</code> here.
   *
   */
  public static final int PORT = 9999;

  /**
   * Describe constant <code>TEXT_FIRST_SEGMENT</code> here.
   *
   */
  public static final String TEXT_FIRST_SEGMENT =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis urna augue, "
          + "porta vitae erat quis, tempus dignissim urna."
          + " Maecenas ornare vel nibh eget fringilla. "
          + "Quisque ac ullamcorper diam."
          + "Vestibulum maximus molestie ex non convallis. Cras id lacinia magna. "
          + "Phasellus finibus turpis erat, id finibus est blandit a."
          + " Integer accumsan est metus, eu placerat dui congue et. "
          + "Phasellus sed fringilla orci."
          + " Nullam aliquet sem sed eros commodo, eget convallis ipsum tristique. "
          + "Etiam ullamcorper, justo eu consequat tempor, tortor libero blandit turpis,"
          + " eu pharetra lectus sapien vitae turpis. "
          + "Ut tincidunt consequat urna sed dignissim.";

  /**
   * Describe constant <code>TEXT_SECOND_SEGMENT</code> here.
   *
   */
  public static final String TEXT_SECOND_SEGMENT =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis urna augue, "
          + "porta vitae erat quis, tempus dignissim urna."
          + " Maecenas ornare vel nibh eget fringilla. "
          + "Quisque ac ullamcorper diam."
          + "Vestibulum maximus molestie ex non convallis. Cras id lacinia magna. "
          + "Phasellus finibus turpis erat, id finibus est blandit a."
          + " Integer accumsan est metus, eu placerat dui congue et. "
          + "Phasellus sed fringilla orci."
          + " Nullam aliquet sem sed eros commodo, eget convallis ipsum tristique. "
          + "Etiam ullamcorper, justo eu consequat tempor, tortor libero blandit turpis,"
          + " eu pharetra lectus sapien vitae turpis. "
          + "Ut tincidunt consequat urna sed dignissim.";

  private Constants() {
    throw new IllegalStateException("Instantiation not allowed");
  }
}
