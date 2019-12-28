package io;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public enum Serialise {
  ;

  public static void main(String[] args) {
    try (ObjectOutputStream outStream = new ObjectOutputStream(
             Files.newOutputStream(Paths.get("personnel.dat")))) {
      Personnel[] staff = {
          new Personnel(123_456, "Smith", "John"),
          new Personnel(234_567, "Jones", "Sally Ann"),
          new Personnel(999_999, "Black", "James Paul"),
      };
      for (Personnel person: staff)
        outStream.writeObject(person);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }

    try (ObjectInputStream inStream = new ObjectInputStream(
             Files.newInputStream(Paths.get("personnel.dat")))) {
      printPersonnel(inStream);
    } catch (EOFException eofEx) {
      System.out.println("\n\n*** End of file ***\n");
    } catch (IOException ioe) {
      System.err.println(ioe);
    } catch (ClassNotFoundException cnfe) {
      System.err.println("Class not found: " + cnfe);
    }
  }

  private static void printPersonnel(ObjectInputStream inStream)
      throws IOException, ClassNotFoundException {
    int staffCount = 0;

    while (staffCount < 3) {
      Personnel person = (Personnel)inStream.readObject();
      staffCount++;
      printPersonnel(person, staffCount);
    }
  }

  private static void printPersonnel(Personnel person, int staffCount) {
    System.out.println("\nStaff member " + staffCount);
    System.out.println("Payroll number: " + person.getPayNum());
    System.out.println("Surname: " + person.getSurname());
    System.out.println("First names: " + person.getFirstNames());
  }
}
