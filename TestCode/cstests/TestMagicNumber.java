package cstests;

public interface TestMagicNumber {
  static byte[] getAsciiRotator() {
    byte[] rotation = new byte[95 * 2];
    for (byte i = ' '; i <= '~'; i++) {
      rotation[i - ' '] = i;
      rotation[i + 95 - ' '] = i;
    }
    return rotation;
  }
}
