package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcScrollableSelect {
  private static Connection connection;
  private static Statement statement;
  private static ResultSet results;

  private JdbcScrollableSelect() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  private static void connect() {
    try {
      connection = DriverManager.getConnection("jdbc:derby:Finances", "", "");
    } catch (SQLException sqlEx) {
      System.err.println("* Cannot connect to database! *");
      System.err.println(sqlEx);
      throw new AssertionError(sqlEx.getMessage(), sqlEx);
    }
  }

  private static void executeQuery() {
    try {
      // clang-format off
      statement =
          connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

      // clang-format on
      results = statement.executeQuery("SELECT * FROM Accounts");
    } catch (SQLException sqlEx) {
      System.out.println("* Cannot execute query! *");
      System.err.println(sqlEx);
      throw new AssertionError(sqlEx.getMessage(), sqlEx);
    }
  }

  private static void forward() {
    try {
      // Iterate through the rows in the forward
      while (results.next())
        // direction, displaying the contents of each
        // row (as in the original program)…
        showRow();
    } catch (SQLException sqlEx) {
      System.err.println("* Error retrieving data! *");
      System.err.println(sqlEx);
      throw new AssertionError(sqlEx.getMessage(), sqlEx);
    }
  }

  private static void backward() {
    try {
      // Cursor for ResultSet is now positioned
      // just after last row, so we can make use
      // of method previous to access the data…
      // Iterate through rows in reverse direction,
      while (results.previous())
        // again displaying contents of each row…
        showRow();
    } catch (SQLException sqlEx) {
      System.err.println("* Error retrieving data! *");
      System.err.println(sqlEx);
      throw new AssertionError(sqlEx.getMessage(), sqlEx);
    }
  }

  public static void main(String[] args) {
    connect();
    executeQuery();
    forward();
    backward();
    close();
  }

  private static void close() {
    try {
      connection.close();
    } catch (SQLException sqlEx) {
      System.err.println("* Unable to disconnect! *");
      System.err.println(sqlEx);
    }
  }

  public static void showRow() throws SQLException {
    System.out.println();
    System.out.println("Account no. " + results.getInt(1));
    System.out.println("Account holder: " + results.getString(3) + " "
                       + results.getString(2));
    System.out.printf("Balance: %.2f %n%n", results.getFloat(4));
  }
}
