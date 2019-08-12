package javapuzzles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum BeerBlast {
  ;
  static final String COMMAND = "java javapuzzles.BeerBlast slave";

  public static void main(String[] args) {
    if (args.length == 1 && args[0].equals("slave")) {
      for (int i = 99; i > 0; i--) {
        System.out.println(i + " bottles of beer on the wall");
        System.out.println(i + " bottles of beer");
        System.out.println("You take one down, pass it around,");
        System.out.println(i - 1 + " bottles of beer on the wall");
        System.out.println();
      }
    } else {
      try {  // Master
        Process process = Runtime.getRuntime().exec(COMMAND);
        drainInBackground(process.getInputStream());
        int exitValue = process.waitFor();
        System.out.println("exit value = " + exitValue);
      } catch (IOException | InterruptedException e) {
        System.err.println(e);
      }
    }
  }

  static void drainInBackground(final InputStream is) {
    new Thread(() -> {
      try {
        BufferedReader bri = new BufferedReader(new InputStreamReader(is));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = bri.readLine()) != null)
          result.append(line).append(System.lineSeparator());
        System.out.println(result.toString());
      } catch (IOException e) {
        // return on IOException
        return;
      }
    }).start();
  }
}
