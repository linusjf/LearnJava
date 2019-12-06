package javapuzzles;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public enum StringCheese {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    try {
      System.out.println("Default charset: " + Charset.defaultCharset());
      byte[] bytes = new byte[256];
      for (int i = 0; i < 256; i++)
        bytes[i] = (byte)i;
      String str = new String(bytes,StandardCharsets.UTF_8);
      int n = str.length();
      for (int i = 0; i < n; i++)
        System.out.print((int)str.charAt(i) + " ");
      System.out.printf("%n");
      System.out.println("Charset: ISO-8859-1");
      str = new String(bytes, "ISO-8859-1");
      n = str.length();
      for (int i = 0; i < n; i++)
        System.out.print((int)str.charAt(i) + " ");
      System.out.printf("%n");
    } catch (UnsupportedEncodingException uee) {
      System.err.println(uee);
    }
  }
}
