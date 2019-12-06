package io;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.PrintWriter;

public enum FileTest1 {
  ;

  public static void main(String[] args) {
    try (PrintWriter output = new PrintWriter(new File("test1.txt"),StandardCharsets.UTF_8.name())) {
      output.println("Single line of text!");
    } catch (IOException exc) {
      System.err.println(exc);
    }
  }
}
