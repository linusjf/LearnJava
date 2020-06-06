package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class BinarySaver {
  private BinarySaver() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    for (String arg: args) {
      try {
        URL root = new URL(arg);
        saveBinaryFile(root);
      } catch (MalformedURLException ex) {
        System.err.println(arg + " is not URL I understand.");
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void saveBinaryFile(URL u) throws IOException {
    URLConnection uc = u.openConnection();
    String contentType = uc.getContentType();
    int contentLength = uc.getContentLength();
    if (contentType.startsWith("text/") || contentLength == -1)
      throw new IOException(u + " is not a binary file.");

    try (InputStream raw = uc.getInputStream();
         InputStream in = new BufferedInputStream(raw);) {
      byte[] data = new byte[contentLength];
      int offset = 0;
      while (offset < contentLength) {
        int bytesRead = in.read(data, offset, data.length - offset);
        if (bytesRead == -1)
          break;
        offset += bytesRead;
      }
      if (offset != contentLength) {
        throw new IOException(u + ": Only read " + offset + " bytes; Expected "
                              + contentLength + " bytes");
      }
      String filename = u.getFile();
      filename = filename.substring(filename.lastIndexOf('/') + 1);
      try (OutputStream out = Files.newOutputStream(Paths.get(filename))) {
        out.write(data);
        out.flush();
      }
    }
  }
}
