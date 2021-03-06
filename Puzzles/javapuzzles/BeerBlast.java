package javapuzzles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@SuppressWarnings({"PMD.SystemPrintln","PMD.LawOfDemeter"})
public enum BeerBlast {
  ;
  static final String COMMAND = "java javapuzzles.BeerBlast slave";

  public static void main(String[] args) {
    if (args.length > 0 && "slave".equals(args[0])) {
      for (int i = 99; i > 0; i--) {
        System.out.println(i + " bottles of beer on the wall");
        System.out.println(i + " bottles of beer");
        System.out.println("You take one down, pass it around,");
        System.out.println(i - 1 + " bottles of beer on the wall");
        System.out.println();
      }
    } else {
      try {
        // Master
        Process process = Runtime.getRuntime().exec(COMMAND);
        drainInBackground(process.getInputStream());
        int exitValue = process.waitFor();
        System.out.println("exit value = " + exitValue);
      } catch (IOException | InterruptedException e) {
        System.err.println(e);
      }
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  static void drainInBackground(final InputStream is) {
    new Thread(() -> drain(is)).start();
  }

  private static void drain(final InputStream is) {
    try {
      BufferedReader bri = new BufferedReader(
          new InputStreamReader(is, StandardCharsets.UTF_8.name()));
      StringBuilder result = new StringBuilder();
      String line = bri.readLine();
      while (line != null) {
        result.append(line).append(System.lineSeparator());
        line = bri.readLine();
      }
      System.out.println(result.toString());
    } catch (IOException e) {
      // return on IOException
      System.err.println(e.getMessage());
    }
  }
}
