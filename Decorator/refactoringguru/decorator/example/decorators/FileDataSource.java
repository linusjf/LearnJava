package refactoringguru.decorator.example.decorators;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataSource implements DataSource {
  private String name;

  public FileDataSource(String name) {
    this.name = name;
  }

  @Override
  public void writeData(String data) {
    File file = new File(name);
    try (OutputStream fos = Files.newOutputStream(Paths.get(file.getPath()))) {
      fos.write(data.getBytes(), 0, data.length());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  @Override
  public String readData() {
    char[] buffer = null;
    File file = new File(name);
    try (Reader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
      buffer = new char[(int) file.length()];
      reader.read(buffer);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
    return new String(buffer);
  }
}
