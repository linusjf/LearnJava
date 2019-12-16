package regex;

import java.util.Scanner;

public enum ScannerRegex {
  ;
  static String dataInput =
      "0.020000  -0.001234 -.5931E-030.014454  -4.00200  -2.23541  0.045117  \n"
      + "222.962   0.600000  30000.0   1.00000   4.82400   0.000     -0.657461 \n"
      + "-1.27151  -0.326195 0.390247  0.787285  -0.451386 -0.486815 -1.27151  \n"
      + "-0.326195 -0.163894 0.286443  1.85980   -0.170646 0.000     0.000     \n"
      + "0.554936  0.505573  -2.31165  -0.170646 0.000     0.000     0.554936  \n"
      + "0.505573  -2.31165  -0.414285 -2.53640  4.54728   2.01358   -0.199695 \n"
      + "4.85477   20.0000   20.0000   20.0000   20.0000   \n";

  private static boolean checkTrimEmpty(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    final String regex = ".{10}";
    String str = null;
    Scanner s = new Scanner(dataInput);
    int i = 1;
    do {
      for (; (str = s.findInLine(regex)) != null; ++i) {
        if (!checkTrimEmpty(str)) {
          System.out.printf("%1$2d ", i);
          System.out.print(" \"" + str + "\" = ");
          System.out.println(Double.valueOf(str));
        }
      }
    } while (s.hasNextLine() && s.nextLine() != null);
    s.close();
  }
}
