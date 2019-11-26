package refactoringguru.decorator.example.decorators;

import java.io.IOException;

public class DataSourceDecorator implements DataSource {
  private final DataSource wrappee;

  DataSourceDecorator(DataSource source) {
    this.wrappee = source;
  }

  @Override
  public void writeData(String data) throws IOException {
    wrappee.writeData(data);
  }

  @Override
  public String readData() throws IOException {
    return wrappee.readData();
  }
}
