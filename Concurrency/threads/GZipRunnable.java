package threads;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {
  private final File input;

  public GZipRunnable(File input) {
    this.input = input;
  }

  @Override
  public void run() {
    // don't compress an already compressed file
    if (!input.getName().endsWith(".gz")) {
      File output = new File(input.getParent(), input.getName() + ".gz");
      if (!output.exists()) {
        try (InputStream in =
                new BufferedInputStream(Files.newInputStream(Paths.get(input.getAbsolutePath())));
            OutputStream out =
                new BufferedOutputStream(
                    new GZIPOutputStream(
                        Files.newOutputStream(Paths.get(output.getAbsolutePath())))); ) {
          int b;
          while ((b = in.read()) != -1) out.write(b);
          out.flush();
        } catch (IOException ex) {
          System.err.println(ex);
        }
      }
    }
  }
}
