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

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  @Override
  public void run() {
    // don't compress an already compressed file
    if (!input.getName().endsWith(".gz")) {
      File output = new File(input.getParent(), input.getName() + ".gz");
      if (!output.exists()) {
        try (
          InputStream in = new BufferedInputStream(Files.newInputStream(Paths.get(input.getAbsolutePath())));
          OutputStream out = new BufferedOutputStream(new GZIPOutputStream(Files.newOutputStream(Paths.get(output.getAbsolutePath()))))) {
          int b;
          while ((b = in.read()) != -1) out.write(b);
          out.flush();
        } catch (IOException ex) {
          System.err.println(ex);
        }
      }
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof GZipRunnable)) return false;
    GZipRunnable other = (GZipRunnable) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$input = this.input;
    Object other$input = other.input;
    if (this$input == null ? other$input != null : !this$input.equals(other$input)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof GZipRunnable;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $input = this.input;
    result = result * PRIME + ($input == null ? 43 : $input.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "GZipRunnable(input=" + this.input + ")";
  }
}
