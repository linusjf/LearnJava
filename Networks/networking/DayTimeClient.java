package networking;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class DayTimeClient {
  public static void main(String[] args) {
    try {
      Date time = Daytime.getDateFromNetwork();
      System.out.println(time);
    } catch (IOException | ParseException ex) {
      System.err.println(ex);
    }
  }
}
