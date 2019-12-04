package refactoringguru.decorator.example.decorators;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator {

  public EncryptionDecorator(DataSource source) {
    super(source);
  }

  @Override
  public void writeData(String data) throws IOException {
    super.writeData(encode(data));
  }

  @Override
  public String readData() throws IOException {
    return decode(super.readData());
  }

  private String encode(String data) {
    byte[] result = data.getBytes(StandardCharsets.UTF_8);
    for (int i = 0; i < result.length; i++) {
      result[i] += (byte)1;
    }
    return Base64.getEncoder().encodeToString(result);
  }

  private String decode(String data) {
    byte[] result = Base64.getDecoder().decode(data);
    for (int i = 0; i < result.length; i++)
      result[i] -= (byte)1;
    return new String(result, StandardCharsets.UTF_8);
  }
}
