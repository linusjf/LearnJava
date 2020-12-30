package serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@SuppressWarnings("PMD.SystemPrintln")
public final class SampleSerialize {
  private SampleSerialize() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String... args) {
    serialize();
    deserialize();
  }

  private static void serialize() {
    try (OutputStream f = Files.newOutputStream(Paths.get("tmp"));
         ObjectOutput s = new ObjectOutputStream(f)) {
      s.writeObject("Today");
      s.writeObject(new Date());
      s.flush();
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static void deserialize() {
    try (InputStream in = Files.newInputStream(Paths.get("tmp"));
         ObjectInputStream s = new ObjectInputStream(in)) {
      String today = (String)s.readObject();
      Date date = (Date)s.readObject();
      System.out.println(today);
      System.out.println(date);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe.getMessage());
    }
  }
}
