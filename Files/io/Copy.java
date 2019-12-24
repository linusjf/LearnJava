package io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum Copy {
  ;
  private static final int NO_OF_ARGS = 2;

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  public static void main(String[] arg) {
    // First check that 2 file names have been
    // supplied…
    if (arg.length < NO_OF_ARGS) {
      System.out.println("You must supply TWO file names.");
      System.out.println("Syntax:");
      System.out.println(" java Copy <source> <destination>");
      return;
    }

    try (Scanner source = new Scanner(new File(arg[0]), UTF_8);
        PrintWriter destination = new PrintWriter(new File(arg[1]), UTF_8); ) {
      String input;
      while (source.hasNext()) {
        input = source.nextLine();
        destination.println(input);
      }
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }
}
