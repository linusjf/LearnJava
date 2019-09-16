package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class JdbcBeanX {
  private int numAccounts = 0;

  public JdbcBeanX() throws JdbcBeanException {
    try (Connection connection =
             DriverManager.getConnection("jdbc:derby:Finances", "", "");
         Statement statement = connection.createStatement();
         ResultSet results =
             statement.executeQuery("SELECT COUNT(*) FROM Accounts");) {
      if (results.next()) {
        numAccounts = results.getInt(1);
      }
    } catch (SQLException sqe) {
      throw new JdbcBeanException(sqe);
    }
  }

  public int getNumAccounts() {
    return numAccounts;
  }

  static class JdbcBeanException extends Exception {
    private static final long serialVersionUID = 1L;

    JdbcBeanException() {
      super();
    }

    JdbcBeanException(String message) {
      super(message);
    }

    JdbcBeanException(String message, Throwable exc) {
      super(message, exc);
    }

    JdbcBeanException(Throwable exc) {
      super(exc);
    }
  }
}
