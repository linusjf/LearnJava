package serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public final class CustomSerialization {
  private CustomSerialization() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String[] args) {
    // Create new User object
    User myDetails = new User("Linus", "Fernandes", 123_456, new Date());
    // Serialization code
    try (OutputStream fileOut = Files.newOutputStream(Paths.get("User.ser"));
         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(myDetails);
    } catch (IOException i) {
      System.err.println(i.getMessage());
    }
    // De-serialization code
    User deserializedUser = null;
    try (InputStream fileIn = Files.newInputStream(Paths.get("User.ser"));
         ObjectInputStream in = new ObjectInputStream(fileIn)) {
      deserializedUser = (User)in.readObject();
      // verify the object state
      System.out.println(deserializedUser.getFirstName());
      System.out.println(deserializedUser.getLastName());
      System.out.println(deserializedUser.getAccountNumber());
      System.out.println(deserializedUser.getDateOpened());
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe.getMessage());
    }
  }
}
