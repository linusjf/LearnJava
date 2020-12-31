package serial;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@SuppressWarnings("PMD.SystemPrintln")
public final class ExternalizableXmlExample {
  private ExternalizableXmlExample() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String[] args) {
    UserSettings settings = new UserSettings();
    settings.setFieldOne(10_000);
    settings.setFieldTwo("HowToDoInJava.com");
    settings.setFieldThree(false);
    System.out.println(settings);
    serializeToXML(settings);
    UserSettings loadedSettings = deserializeFromXML();
    System.out.println(loadedSettings);
  }

  private static void serializeToXML(UserSettings settings) {
    try (OutputStream fos = Files.newOutputStream(Paths.get("settings.xml"));
         XMLEncoder encoder = new XMLEncoder(fos);) {
      encoder.setExceptionListener(
          e -> System.out.println("Exception! :" + e.toString()));
      encoder.writeObject(settings);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static UserSettings deserializeFromXML() {
    UserSettings decodedSettings = null;
    try (InputStream fis = Files.newInputStream(Paths.get("settings.xml"));
         XMLDecoder decoder = new XMLDecoder(fis);) {
      decodedSettings = (UserSettings)decoder.readObject();
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    return decodedSettings;
  }
}
