package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcBean {
  private List<Object> acctDetails;

  public JdbcBean() throws JdbcBeanException {
    try (
      Connection connection = DriverManager.getConnection(
        "jdbc:derby:Finances",
        "",
        ""
      );
      Statement statement = connection.createStatement();
      ResultSet results = statement.executeQuery("SELECT * FROM Accounts");
    ) {
      acctDetails = new ArrayList<>();
      while (results.next()) {
        acctDetails.add(results.getInt(1));
        acctDetails.add(results.getString(3) + " " + results.getString(2));
        acctDetails.add(results.getFloat(4));
      }
    } catch (SQLException sqe) {
      throw new JdbcBeanException(sqe);
    }
  }

  public List<Object> getAcctDetails() {
    return acctDetails;
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
