package io;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public enum ArrayListSerialise {
  ;

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    ArrayList<Personnel> staffListOut = new ArrayList<>();
    ArrayList<Personnel> staffListIn = new ArrayList<>();
    try (ObjectOutputStream outStream = new ObjectOutputStream(
             Files.newOutputStream(Paths.get("personnel.dat")))) {
      // clang-format off
      Personnel[] staff = {
        new Personnel(123_456, "Smith", "John"),
        new Personnel(234_567, "Jones", "Sally Ann"),
        new Personnel(999_999, "Black", "James Paul")
      };
      // clang-format on
      for (Personnel person: staff)
        staffListOut.add(person);
      outStream.writeObject(staffListOut);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }

    try (ObjectInputStream inStream = new ObjectInputStream(
             Files.newInputStream(Paths.get("personnel.dat")))) {
      int staffCount = 0;
      staffListIn = (ArrayList<Personnel>)inStream.readObject();
      // The compiler will issue a warning for the
      // above line, but ignore this!
      for (Personnel person: staffListIn) {
        staffCount++;
        System.out.println("\nStaff member " + staffCount);
        System.out.println("Payroll number: " + person.getPayNum());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("First names: " + person.getFirstNames());
      }
      System.out.println("\n");
    } catch (EOFException eofEx) {
      System.out.println("\n\n*** End of file ***\n");
    } catch (IOException ioe) {
      System.err.println(ioe);
    } catch (ClassNotFoundException cnfe) {
      System.err.println("Class not found: " + cnfe);
    }
  }
}
