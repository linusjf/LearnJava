package networking;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class EncoderTest {
  private static final String UTF8 = "UTF-8";

  private EncoderTest() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    try {
      System.out.println(URLEncoder.encode("This string has spaces", UTF8));
      System.out.println(URLEncoder.encode("This*string*has*asterisks", UTF8));
      System.out.println(
          URLEncoder.encode("This%string%has%percent%signs", UTF8));
      System.out.println(URLEncoder.encode("This+string+has+pluses", UTF8));
      System.out.println(URLEncoder.encode("This/string/has/slashes", UTF8));
      System.out.println(
          URLEncoder.encode("This\"string\"has\"quote\"marks", UTF8));
      System.out.println(URLEncoder.encode("This:string:has:colons", UTF8));
      System.out.println(URLEncoder.encode("This~string~has~tildes", UTF8));
      System.out.println(
          URLEncoder.encode("This(string)has(parentheses)", UTF8));
      System.out.println(URLEncoder.encode("This.string.has.periods", UTF8));
      System.out.println(
          URLEncoder.encode("This=string=has=equals=signs", UTF8));
      System.out.println(URLEncoder.encode("This&string&has&ampersands", UTF8));
      System.out.println(
          URLEncoder.encode("Thiséstringéhasé non-ASCII characters", UTF8));
    } catch (UnsupportedEncodingException ex) {
      throw new EncodingException("Broken VM does not support UTF-8", ex);
    }
  }
}
