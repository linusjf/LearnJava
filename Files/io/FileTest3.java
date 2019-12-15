package io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum FileTest3 {
  ;
  private static final String UTF_8 = 
    StandardCharsets.UTF_8.name();
  
  public static void main(String[] args) {
    int mark;
    int total = 0;
    int count = 0;
    try (
      Scanner input = new Scanner(
        new File("marks1.txt"),
        UTF_8
      )
    ) {
      while (input.hasNext()) {
        mark = input.nextInt();
        total += mark;
        count++;
      }
      System.out.println("Mean = " + (float) total / count);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }
}
