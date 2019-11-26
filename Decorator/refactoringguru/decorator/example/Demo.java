package refactoringguru.decorator.example;

import java.io.IOException;
import refactoringguru.decorator.example.decorators.CompressionDecorator;
import refactoringguru.decorator.example.decorators.DataSource;
import refactoringguru.decorator.example.decorators.DataSourceDecorator;
import refactoringguru.decorator.example.decorators.EncryptionDecorator;
import refactoringguru.decorator.example.decorators.FileDataSource;

@SuppressWarnings("PMD.ShortClassName")
public final class Demo {

  private Demo() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      String salaryRecords =
          "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
      DataSourceDecorator encoded = new CompressionDecorator(
          new EncryptionDecorator(new FileDataSource("OutputDemo.txt")));
      encoded.writeData(salaryRecords);
      DataSource plain = new FileDataSource("OutputDemo.txt");

      System.out.println("- Input ----------------");
      System.out.println(salaryRecords);
      System.out.println("- Encoded --------------");
      System.out.println(plain.readData());
      System.out.println("- Decoded --------------");
      System.out.println(encoded.readData());
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }
}
