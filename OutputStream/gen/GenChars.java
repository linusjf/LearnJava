package gen;

import java.io.IOException;
import java.io.OutputStream;

public enum GenChars {
  ;

  public static void main(String[] args) {
    try {
    generateCharacters(System.out);
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private static void generateCharacters(OutputStream out) throws IOException {
    int firstPrintableCharacter = 33;
    int numberOfPrintableCharacters = 94;
    int numberOfCharactersPerLine = 72;
    int start = firstPrintableCharacter;
    int iterCount = 0;
    while (iterCount < numberOfPrintableCharacters) { /* infinite loop */
      for (int i = start; i < start + numberOfCharactersPerLine; i++) {
        out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters)
            + firstPrintableCharacter);
      }
      out.write('\r'); // carriage return
      out.write('\n'); // linefeed
      start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters
          + firstPrintableCharacter;
      iterCount++;
    }
  }
}
