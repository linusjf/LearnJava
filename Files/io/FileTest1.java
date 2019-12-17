package io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public enum FileTest1 {
  ;
  private static final String UTF_8 =
      StandardCharsets.UTF_8.name();

  public static void main(String[] args) {
    try (PrintWriter output = new PrintWriter(
             new File("test1.txt"), UTF_8)) {
      output.println("Single line of text!");
    } catch (IOException exc) {
      System.err.println(exc);
    }
  }
}
