package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public enum Serialise {
  ;
  public static void main(String[] args) {

    try (ObjectOutputStream outStream =
             new ObjectOutputStream(new FileOutputStream("personnel.dat"))) {
      Personnel[] staff = {new Personnel(123456, "Smith", "John"),
                           new Personnel(234567, "Jones", "Sally Ann"),
                           new Personnel(999999, "Black", "James Paul")};
      for (Personnel person: staff)
        outStream.writeObject(person);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }

    try (ObjectInputStream inStream =
             new ObjectInputStream(new FileInputStream("personnel.dat"))) {
      int staffCount = 0;

      while (staffCount < 3) {
        Personnel person = (Personnel)inStream.readObject();
        staffCount++;
        System.out.println("\nStaff member " + staffCount);
        System.out.println("Payroll number: " + person.getPayNum());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("First names: " + person.getFirstNames());
      }
    } catch (EOFException eofEx) {
      System.out.println("\n\n*** End of file ***\n");
    } catch (IOException ioe) {
      System.err.println(ioe);
    } catch (ClassNotFoundException cnfe) {
      System.err.println("Class not found: " + cnfe);
    }
  }
}
