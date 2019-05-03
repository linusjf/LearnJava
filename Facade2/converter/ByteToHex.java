package converter;
/* @author      : Linus Fernandes (linusfernandes@gmail.com)
 * @file        : ByteToHex.java
 * @created     : Friday May 03, 2019 21:59:40 IST
 * @copyright   : Copyright (c) Linus Fernandes
 * @description : 
*/
public final class ByteToHex {

    private static final String HEXES = "0123456789abcdef";

    public static String getHex(byte[] raw) {
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b: raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static String getHex2(byte[] raw) {
        final StringBuilder builder = new StringBuilder(2 * raw.length);
        for (final byte b: raw)
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

        return builder.toString();
    }

    public static String getHex3(byte[] raw) {
        final StringBuilder builder = new StringBuilder(2 * raw.length);
        for (final byte b: raw)
            builder.append(String.format("%02x", b));
        return builder.toString();
    }

    public static String getHex4(byte[] raw) {
        final StringBuilder builder = new StringBuilder(2 * raw.length);
        for (final byte b: raw)
    builder.append(Character.forDigit((b >> 4) & 0xF, 16)).append(Character.forDigit((b & 0xF), 16));
        return builder.toString();
    }
}
