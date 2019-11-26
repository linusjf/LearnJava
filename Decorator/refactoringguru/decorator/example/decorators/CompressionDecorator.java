package refactoringguru.decorator.example.decorators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionDecorator extends DataSourceDecorator {
  private int compLevel = 6;

  public CompressionDecorator(DataSource source) {
    super(source);
  }

  public int getCompressionLevel() {
    return compLevel;
  }

  public void setCompressionLevel(int value) {
    compLevel = value;
  }

  @Override
  public void writeData(String data) throws IOException {
    super.writeData(compress(data));
  }

  @Override
  public String readData() throws IOException {
    return decompress(super.readData());
  }

  private String compress(String stringData) throws IOException {
    byte[] data = stringData.getBytes();
    try (ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
         DeflaterOutputStream dos =
             new DeflaterOutputStream(bout, new Deflater(compLevel));) {
      dos.write(data);
      return Base64.getEncoder().encodeToString(bout.toByteArray());
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private String decompress(String stringData) throws IOException {
    byte[] data = Base64.getDecoder().decode(stringData);
    try (InputStream in = new ByteArrayInputStream(data);
         InflaterInputStream iin = new InflaterInputStream(in);
         ByteArrayOutputStream bout = new ByteArrayOutputStream(512);) {
      int b;
      while ((b = iin.read()) != -1)
        bout.write(b);
      return new String(bout.toByteArray());
    }
  }
}
