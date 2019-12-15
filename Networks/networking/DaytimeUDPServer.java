package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.FormatLogger;

public enum DaytimeUDPServer {
  ;
  private static final int PORT = 1313;
  private static final FormatLogger AUDIT = new FormatLogger(
    Logger.getLogger("requests")
  );
  private static final FormatLogger ERRORS = new FormatLogger(
    Logger.getLogger("errors")
  );

  public static void main(String[] args) {
    try (DatagramSocket socket = new DatagramSocket(PORT)) {
      while (true) {
        try {
          DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
          socket.receive(request);
          String daytime = new Date().toString();
          byte[] data = daytime.getBytes("US-ASCII");
          DatagramPacket response = new DatagramPacket(
            data,
            data.length,
            request.getAddress(),
            request.getPort()
          );
          socket.send(response);
          AUDIT.info("%s %s", daytime, request.getAddress());
        } catch (IOException ex) {
          ERRORS.log(Level.SEVERE, "%s: %s", ex.getMessage(), ex);
        }
      }
    } catch (IOException ex) {
      ERRORS.log(Level.SEVERE, "%s: %s", ex.getMessage(), ex);
    }
  }
}
