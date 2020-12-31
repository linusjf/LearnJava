package serial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class DemoClass implements java.io.Serializable {

  // Default serial version uid
  private static final long serialVersionUID = 1L;
  // Any random name
  private static final String FILE_NAME = "DemoClassBytes.ser";
  private static final Logger LOGGER =
      Logger.getLogger(DemoClass.class.getName());
  // Few data fields
  // Able to serialize
  private static String staticVariable;
  private int intVariable;

  // Not able to serialize
  @SuppressWarnings("PMD")
  private transient String transientVariable =
      "this is a transient instance field";

  @SuppressWarnings("PMD")
  private Thread threadClass;

  public static void main(String[] args)
      throws IOException, ClassNotFoundException {
    // Serialization

    DemoClass test = new DemoClass();
    test.intVariable = 1;
    staticVariable = "this is a static variable";
    writeOut(test);
    System.out.println("DemoClass to be saved: " + test);

    // De-serialization

    System.out.println("DemoClass deserialized: " + readIn());
  }

  private static Object readIn() throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(
             Files.newInputStream(Paths.get(FILE_NAME)))) {
      return ois.readObject();
    }
  }

  private static void writeOut(java.io.Serializable obj) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(
             Files.newOutputStream(Paths.get(FILE_NAME)))) {
      oos.writeObject(obj);
    }
  }

  @Override
  public String toString() {
    return "DemoClass: final static fileName = " + FILE_NAME
        + ", final static logger = " + LOGGER
        + ", non-final static staticVariable = " + staticVariable
        + ", instance intVariable = " + intVariable
        + ", transient instance transientVariable = " + transientVariable
        + ", non-serializable instance field threadClass = " + threadClass;
  }
}
