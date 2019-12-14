package io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum FileTest2 {
  ;

  public static void main(String[] args) {
    try (Scanner input = new Scanner(System.in, StandardCharsets.UTF_8.name())) {
      System.out.print("Enter file name: ");
      String fileName = input.nextLine();
      inputMarks(input, fileName);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static void inputMarks(Scanner input, String fileName) throws IOException {
    try (PrintWriter output = new PrintWriter(new File(fileName), StandardCharsets.UTF_8.name())) {
      System.out.printf("Ten marks needed.%n");
      for (int i = 1; i < 11; i++) {
        if (input.hasNext()) {
          System.out.print("Enter mark " + i + ": ");
          int mark = input.nextInt();
          output.println(mark);
          output.flush();
        }
      }
    }
  }
}
