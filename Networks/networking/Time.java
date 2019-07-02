package networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("PMD.ShortClassName")
public final class Time {
  private static final String HOSTNAME = "time.nist.gov";

  private Time() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("fallthrough")
  public static void main(String[] args) {
    try {
      Date d;
      switch (args.length) {
        case 0:
          d = Time.getDateFromNetwork();
          break;

        case 1:
          d = Time.getDateFromNetwork(args[0], 37);
          break;

        default:
          d = Time.getDateFromNetwork(args[0], Integer.parseInt(args[1]));
      }
      System.out.println("It is " + d);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public static Date getDateFromNetwork() throws IOException {
    return getDateFromNetwork(HOSTNAME, 37);
  }

  public static Date getDateFromNetwork(String host, int port)
      throws IOException {
    // The time protocol sets the epoch at 1900,
    // the Java Date class at 1970. This number
    // converts between them.
    // long differenceBetweenEpochs = 2208988800L;
    TimeZone gmt = TimeZone.getTimeZone("GMT");
    Calendar epoch1900 = Calendar.getInstance(gmt);
    epoch1900.set(1900, 01, 01, 00, 00, 00);
    long epoch1900ms = epoch1900.getTime().getTime();
    Calendar epoch1970 = Calendar.getInstance(gmt);
    epoch1970.set(1970, 01, 01, 00, 00, 00);
    long epoch1970ms = epoch1970.getTime().getTime();
    long differenceInMS = epoch1970ms - epoch1900ms;
    long differenceBetweenEpochs = differenceInMS / 1000;
    Socket socket = new Socket(host, port);
    socket.setSoTimeout(15_000);
    InputStream raw = socket.getInputStream();

    long secondsSince1900 = 0;
    for (int i = 0; i < 4; i++) {
      secondsSince1900 = (secondsSince1900 << 8) | raw.read();
    }
    long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
    long msSince1970 = secondsSince1970 * 1000;
    return new Date(msSince1970);
  }
}
