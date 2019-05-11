/**
 * @author : Linus Fernandes (linusfernandes@gmail.com)
 * @file : TestByteToHex.java
 * @created : Friday May 03, 2019 20:08:16 IST
 * @copyright : Copyright (c) Linus Fernandes
 * @description :
 */
public class TestByteToHex {
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final String byteString =
        "@#£&_-()=%?!/:'*\"[]{}<>^¡¿~™®©¢¥€"
            + "$123456789003356788990335688335678888"
            + "))5778889===66://))*£&'/!!))))?:/!?"
            + "?????!//!!!!!!!!!?????      dffvbbfrews"
            + "hjoohgvvvzscvbmmmxxvffew236889uygghhbhjk"
            + "iu65fvbhbbvvvvvdew13yhgftggjioo9hhgggg"
            + "gvvgdWeryhhhDFGJKYRESCHJKKOKVVCSSDVNJH"
            + "FDSSSGHIJJH";
    final byte[] raw = byteString.getBytes();
    long start = System.nanoTime();
    final String hex = ByteToHex.getHex(raw);
    long end = System.nanoTime();
    long elapsed = end - start;
    System.out.println("getHex: " + elapsed);

    start = System.nanoTime();
    final String hex2 = ByteToHex.getHex2(raw);
    end = System.nanoTime();
    elapsed = end - start;
    System.out.println("getHex2: " + elapsed);

    start = System.nanoTime();
    final String hex3 = ByteToHex.getHex3(raw);
    end = System.nanoTime();
    elapsed = end - start;
    System.out.println("getHex3: " + elapsed);

    start = System.nanoTime();
    final String hex4 = ByteToHex.getHex4(raw);
    end = System.nanoTime();
    elapsed = end - start;
    System.out.println("getHex4: " + elapsed);
    assert ((hex == hex2) && (hex2 == hex3) && (hex3 == hex4));
  }
}
