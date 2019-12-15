package refactoringguru.decorator.example.decorators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionDecorator extends DataSourceDecorator {
  private int compLevel = 6;
  private final Base64.Encoder encoder = Base64.getEncoder();
  private final Base64.Decoder decoder = Base64.getDecoder();

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

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public String readData() throws IOException {
    return decompress(super.readData());
  }

  private String compress(String stringData) throws IOException {
    byte[] data = stringData.getBytes(StandardCharsets.UTF_8);
    try (
      ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
      DeflaterOutputStream dos = new DeflaterOutputStream(
        bout,
        new Deflater(compLevel)
      );
    ) {
      dos.write(data);
      return encoder.encodeToString(bout.toByteArray());
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private String decompress(String stringData) throws IOException {
    byte[] data = decoder.decode(stringData);
    try (
      InputStream in = new ByteArrayInputStream(data);
      InflaterInputStream iin = new InflaterInputStream(in);
      ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
    ) {
      int b;
      while ((b = iin.read()) != -1) bout.write(b);
      return new String(bout.toByteArray(), StandardCharsets.UTF_8);
    }
  }
}
