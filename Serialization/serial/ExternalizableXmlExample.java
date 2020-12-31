package serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
         ObjectOutputStream oos = new ObjectOutputStream(fos);) {
      settings.writeExternal(oos);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private static UserSettings deserializeFromXML() {
    UserSettings decodedSettings = new UserSettings();
    try (InputStream fis = Files.newInputStream(Paths.get("settings.xml"));
         ObjectInputStream ois = new ObjectInputStream(fis);) {
      decodedSettings.readExternal(ois);
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe.getMessage());
    }
    return decodedSettings;
  }
}
