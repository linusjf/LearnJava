package io;

import static io.RanFileConstants.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum RanFile1 {
  ;
  private static long acctNum;
  private static String surname;
  private static String initials;
  private static float balance;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  public static void main(String[] args) {
    try (Scanner input = new Scanner(System.in, UTF_8);
        RandomAccessFile ranAccts = new RandomAccessFile("accounts.dat", "rw"); ) {
      String reply = "y";
      while ("y".equalsIgnoreCase(reply)) {
        acctNum++;
        System.out.println("\nAccount number " + acctNum + ".\n");
        System.out.print("Surname: ");
        surname = input.nextLine();
        System.out.print("Initial(s): ");
        initials = input.nextLine();
        System.out.print("Balance: ");
        balance = input.nextFloat();

        // Now get rid of carriage return(!)…
        input.nextLine();
        writeRecord(ranAccts);

        // Method defined below.
        System.out.print("\nDo you wish to do this again (y/n)? ");
        reply = input.nextLine();
      }
      System.out.println();
      showRecords(ranAccts);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }

  public static void writeRecord(RandomAccessFile file) throws IOException {
    // First find starting byte for current record…
    long filePos = (acctNum - 1) * REC_SIZE;

    // Position file pointer…
    file.seek(filePos);

    // Now write the four (fixed-size) fields.
    // Note that a definition must be provided
    // for method writeString…
    file.writeLong(acctNum);
    writeString(file, surname, SURNAME_SIZE);
    writeString(file, initials, NUM_INITS);
    file.writeFloat(balance);
  }

  public static void writeString(RandomAccessFile file, String text, int fixedSize)
      throws IOException {
    int size = text.length();
    if (size <= fixedSize) {
      file.writeChars(text);

      // Now 'pad out' the field with spaces…
      for (int i = size; i < fixedSize; i++) file.writeChar(' ');
    } else {
      // String is too long!
      file.writeChars(text.substring(0, fixedSize));
      // Write to file the first fixedSize characters of
      // string text, starting at byte zero.
    }
  }

  public static void showRecords(RandomAccessFile file) throws IOException {
    long numRecords = file.length() / REC_SIZE;
    file.seek(0);

    // Go to start of file.
    for (int i = 0; i < numRecords; i++) {
      acctNum = file.readLong();
      surname = readString(file, SURNAME_SIZE);

      // readString defined below.
      initials = readString(file, NUM_INITS);
      balance = file.readFloat();
      System.out.printf(acctNum + " " + surname + " " + initials + " " + "%.2f %n", balance);
    }
  }

  public static String readString(RandomAccessFile file, int fixedSize) throws IOException {
    StringBuilder value = new StringBuilder();

    // Set up empty string.
    // Read character and concatenate it onto value…
    for (int i = 0; i < fixedSize; i++) value.append(file.readChar());
    return value.toString();
  }
}
