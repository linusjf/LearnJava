package io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public enum FileTest2 {
  ;

  public static void main(String[] args) {
    String fileName;
    int mark;
    try (Scanner input = new Scanner(System.in,StandardCharsets.UTF_8.name())) {
      System.out.print("Enter file name: ");
      fileName = input.nextLine();
      PrintWriter output = new PrintWriter(new File(fileName),StandardCharsets.UTF_8.name());
      System.out.println("Ten marks needed.\n");
      for (int i = 1; i < 11; i++) {
        if (input.hasNext()) {
          System.out.print("Enter mark " + i + ": ");
          mark = input.nextInt();

          // * Should really validate entry! *
          output.println(mark);
          output.flush();
        }
      }
      output.close();
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }
}
