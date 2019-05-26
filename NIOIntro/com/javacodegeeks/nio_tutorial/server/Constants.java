package com.javacodegeeks.nio_tutorial.server;

public final class Constants {

  public static final int CLIENT_BYTE_BUFFER_CAPACITY = 1024;
  public static final String HOST = "localhost";
  public static final int PORT = 9999;

  public static final String TEXT_FIRST_SEGMENT =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis urna augue, "
          + "porta vitae erat quis, tempus dignissim urna. Maecenas ornare vel nibh eget fringilla. "
          + "Quisque ac ullamcorper diam. Vestibulum maximus molestie ex non convallis. Cras id lacinia magna. "
          + "Phasellus finibus turpis erat, id finibus est blandit a. Integer accumsan est metus, eu placerat dui congue et. "
          + "Phasellus sed fringilla orci. Nullam aliquet sem sed eros commodo, eget convallis ipsum tristique. "
          + "Etiam ullamcorper, justo eu consequat tempor, tortor libero blandit turpis, eu pharetra lectus sapien vitae turpis. "
          + "Ut tincidunt consequat urna sed dignissim.";

  public static final String TEXT_SECOND_SEGMENT =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis urna augue, "
          + "porta vitae erat quis, tempus dignissim urna. Maecenas ornare vel nibh eget fringilla. "
          + "Quisque ac ullamcorper diam. Vestibulum maximus molestie ex non convallis. Cras id lacinia magna. "
          + "Phasellus finibus turpis erat, id finibus est blandit a. Integer accumsan est metus, eu placerat dui congue et. "
          + "Phasellus sed fringilla orci. Nullam aliquet sem sed eros commodo, eget convallis ipsum tristique. "
          + "Etiam ullamcorper, justo eu consequat tempor, tortor libero blandit turpis, eu pharetra lectus sapien vitae turpis. "
          + "Ut tincidunt consequat urna sed dignissim.";

  private Constants() {
    throw new IllegalStateException("Instantiation not allowed");
  }
}
