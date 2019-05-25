package converter;

/**
 * Describe class <code>ByteToHex</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class ByteToHex { // NOPMD

  private static final String HEXES = "0123456789abcdef";

  private ByteToHex() {
  }

  /**
   * Describe <code>getHex</code> method here.
   *
   * @param raw a <code>byte</code> value
   * @return a <code>String</code> value
   */
  public static String getHex(byte[] raw) {
    final StringBuilder hex = new StringBuilder(2 * raw.length);
    for (final byte b : raw) hex.append(HEXES.charAt(b & 0xF0 >> 4)).append(HEXES.charAt(b & 0x0F));
    return hex.toString();
  }

  /**
   * Describe <code>getHex2</code> method here.
   *
   * @param raw a <code>byte</code> value
   * @return a <code>String</code> value
   */
  public static String getHex2(byte[] raw) {
    final StringBuilder builder = new StringBuilder(2 * raw.length);
    for (final byte b : raw) builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
    return builder.toString();
  }

  /**
   * Describe <code>getHex3</code> method here.
   *
   * @param raw a <code>byte</code> value
   * @return a <code>String</code> value
   */
  public static String getHex3(byte[] raw) {
    final StringBuilder builder = new StringBuilder(2 * raw.length);
    for (final byte b : raw) builder.append(String.format("%02x", b));
    return builder.toString();
  }

  /**
   * Describe <code>getHex4</code> method here.
   *
   * @param raw a <code>byte</code> value
   * @return a <code>String</code> value
   */
  public static String getHex4(byte[] raw) {
    final StringBuilder builder = new StringBuilder(2 * raw.length);
    for (final byte b : raw)
      builder.append(Character.forDigit(b >> 4 & 0xF, 16)).append(Character.forDigit(b & 0xF, 16));
    return builder.toString();
  }
}
