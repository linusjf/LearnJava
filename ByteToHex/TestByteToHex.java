/**
 * @author      : Linus Fernandes (linusfernandes@gmail.com)
 * @file        : TestByteToHex.java
 * @created     : Friday May 03, 2019 20:08:16 IST
 * @copyright   : Copyright (c) Linus Fernandes
 * @description : 
 */
public class TestByteToHex {
    public static void main(String[] args) {
        long start, end, elapsed;
        String hex;
        String byteString = "@#£&_-()=%?!/:'*\"[]{}<>^¡¿~™®©¢¥€$123456789003356788990335688335678888))5778889===66://))*£&'/!!))))?:/!??????!//!!!!!!!!!?????      dffvbbfrewshjoohgvvvzscvbmmmxxvffew236889uygghhbhjkiu65fvbhbbvvvvvdew13yhgftggjioo9hhgggggvvgdWeryhhhDFGJKYRESCHJKKOKVVCSSDVNJHFDSSSGHIJJH";
        byte[] raw = byteString.getBytes();
        start = System.nanoTime();
        hex = ByteToHex.getHex(raw);
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("getHex: "+elapsed);

        start = System.nanoTime();
        hex = ByteToHex.getHex2(raw);
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("getHex2: "+elapsed);
        
        start = System.nanoTime();
        hex = ByteToHex.getHex3(raw);
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("getHex3: "+elapsed);
        
        start = System.nanoTime();
        hex = ByteToHex.getHex4(raw);
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("getHex4: "+elapsed);
    }
}
