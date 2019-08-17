package networking;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public final class TimeServer {
  public static final int PORT = 3737;

  private TimeServer() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    // The time protocol sets the epoch at 1900,
    // the Date class at 1970. This number
    // converts between them.
    long differenceBetweenEpochs = 2_208_988_800L;
    try (ServerSocket server = new ServerSocket(PORT)) {
      while (true) {
        try (Socket connection = server.accept();
             OutputStream out = connection.getOutputStream();) {
          Date now = new Date();
          long msSince1970 = now.getTime();
          long secondsSince1970 = msSince1970 / 1000;
          long secondsSince1900 = secondsSince1970 + differenceBetweenEpochs;
          byte[] time = new byte[4];
          time[0] = (byte) ((secondsSince1900 & 0x00000000FF000000L) >> 24);
          time[1] = (byte) ((secondsSince1900 & 0x0000000000FF0000L) >> 16);
          time[2] = (byte) ((secondsSince1900 & 0x000000000000FF00L) >> 8);
          time[3] = (byte) (secondsSince1900 & 0x00000000000000FFL);
          out.write(time);
          out.flush();
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
