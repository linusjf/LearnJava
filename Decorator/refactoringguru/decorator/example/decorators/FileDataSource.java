package refactoringguru.decorator.example.decorators;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataSource implements DataSource {
  private final String name;

  public FileDataSource(String name) {
    this.name = name;
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  @Override
  public void writeData(String data) throws IOException {
    File file = new File(name);
    try (OutputStream fos = Files.newOutputStream(Paths.get(file.getPath()))) {
      fos.write(data.getBytes(), 0, data.length());
    }
  }

  @Override
  public String readData() throws IOException {
    char[] buffer;
    File file = new File(name);
    try (Reader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
      buffer = new char[(int)file.length()];
      reader.read(buffer);
    }
    return new String(buffer);
  }
}
