package refactoringguru.decorator.example.decorators;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
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
      fos.write(data.getBytes(StandardCharsets.UTF_8), 0, data.length());
    }
  }

  @Override
  public String readData() throws IOException {
    File file = new File(name);
    try (Reader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
      char[] buffer = new char[(int) file.length()];
      if (reader.read(buffer) == -1) throw new IOException("Error reading into buffer.");
      else return new String(buffer);
    }
  }
}
