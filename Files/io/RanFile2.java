package io;

// Allows the user to retrieve individual account
// records and modify their balances.
import static io.RanFileConstants.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum RanFile2 {
  ;
  private static long acctNum;
  private static String surname;
  private static String initials;
  private static float balance;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    try (
      Scanner input = new Scanner(System.in, StandardCharsets.UTF_8.name());
      RandomAccessFile ranAccts = new RandomAccessFile("accounts.dat", "rw");
    ) {
      long numRecords = ranAccts.length() / REC_SIZE;
      String reply = "y";

      while ("y".equalsIgnoreCase(reply)) {
        System.out.printf("%nEnter account number: ");
        acctNum = input.nextLong();
        while (acctNum < 1 || acctNum > numRecords) {
          System.out.printf("%n*** Invalid number! ***%n%n");
          System.out.printf("%nEnter account number: ");
          acctNum = input.nextLong();
        }
        showRecord(ranAccts);

        // Defined below.
        System.out.printf("%nEnter new balance: ");
        balance = input.nextFloat();
        input.nextLine();

        // Get rid of carriage return!
        long currentPos = ranAccts.getFilePointer();
        ranAccts.seek(currentPos - 4);

        // Back 4 bytes.
        ranAccts.writeFloat(balance);
        System.out.printf("%nModify another balance (y/n)? ");
        reply = input.nextLine();
      }
    } catch (IOException exc) {
      System.err.println(exc);
    }
  }

  public static void showRecord(RandomAccessFile file) throws IOException {
    file.seek((acctNum - 1) * REC_SIZE);
    acctNum = file.readLong();
    surname = readString(file, SURNAME_SIZE);
    initials = readString(file, NUM_INITS);
    balance = file.readFloat();
    System.out.println("Surname: " + surname);
    System.out.println("Initials: " + initials);
    System.out.printf("Balance: %.2f %n", balance);
  }

  public static String readString(RandomAccessFile file, int fixedSize)
    throws IOException {
    // Set up empty buffer before reading from file…
    StringBuilder buffer = new StringBuilder();

    // Read character from file and append to buffer.
    for (int i = 0; i < fixedSize; i++) buffer.append(file.readChar());
    return buffer.toString();
  // Convert into String.
  }
}
