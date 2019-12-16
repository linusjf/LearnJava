package gen;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public enum GenChars {
  ;
  private static final String TMP_FILE_PATH = "/tmp/data.txt";

  public static void main(String[] args) {
    try {
      generateCharacters(System.out);
      writeToFile();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private static void writeToFile() throws IOException {
    try (OutputStream out = Files.newOutputStream(Paths.get(TMP_FILE_PATH))) {
      generateCharacters(out);
    }
  }

  private static void generateCharacters(OutputStream out) throws IOException {
    int firstPrintableCharacter = 33;
    int numberOfPrintableCharacters = 94;
    int numberOfCharactersPerLine = 72;
    int start = firstPrintableCharacter;

    byte[] line = new byte[numberOfCharactersPerLine + 2];

    int iterCount = 0;
    while (iterCount < numberOfPrintableCharacters) {
      /* infinite loop */
      for (int i = start; i < start + numberOfCharactersPerLine; i++) {
        line[i - start] = (byte) ((i - firstPrintableCharacter) % numberOfPrintableCharacters
            + firstPrintableCharacter);
      }
      line[72] = (byte) '\r';

      // carriage return
      line[73] = (byte) '\n';

      // line feed
      out.write(line);
      start = (start + 1 - firstPrintableCharacter) % numberOfPrintableCharacters
          + firstPrintableCharacter;
      iterCount++;
    }
  }
}
