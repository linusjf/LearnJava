package networking;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public final class DayTimeClient {

  private DayTimeClient() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      if (args.length == 0) {
        Date time = Daytime.getDateFromNetwork();
        System.out.println(time);
        System.exit(0);
      }
      if (args.length == 1) {
        Date time = Daytime.getDateFromNetwork(args[0], 13);
        System.out.println(time);
        System.exit(0);
      }
      Date time = Daytime.getDateFromNetwork(
        args[0],
        Integer.parseInt(args[1])
      );
      System.out.println(time);
      System.exit(0);
    } catch (IOException | ParseException ex) {
      System.err.println(ex);
    }
  }
}
