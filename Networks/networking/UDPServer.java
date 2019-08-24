package networking;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.SocketException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import logging.FormatLogger;
import java.util.logging.Logger;
import java.util.logging.Level;

public abstract class UDPServer implements Runnable {
  private final int bufferSize;  // in bytes
  private final int port;
  private final FormatLogger logger =
      new FormatLogger(Logger.getLogger(UDPServer.class.getCanonicalName()));
  private volatile boolean isShutDown = false;

  public UDPServer(int port, int bufferSize) {
    this.bufferSize = bufferSize;
    this.port = port;
  }

  public UDPServer(int port) {
    this(port, 8192);
  }

  @Override
  public void run() {
    byte[] buffer = new byte[bufferSize];
    try (DatagramSocket socket = new DatagramSocket(port)) {
      socket.setSoTimeout(10000);  // check every 10 seconds for shutdown
      while (true) {
        if (isShutDown)
          return;
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        try {
          socket.receive(incoming);
          this.respond(socket, incoming);
        } catch (SocketTimeoutException ex) {
          if (isShutDown)
            return;
        } catch (IOException ex) {
          logger.log(Level.WARNING, "%s: %s",ex.getMessage(), ex);
        }
      }  // end while
    } catch (SocketException ex) {
      logger.log(Level.SEVERE, "Could not bind to port %d: %s",port, ex);
    }
  }

  public abstract void respond(DatagramSocket socket, DatagramPacket request)
      throws IOException;

  public void shutDown() {
    this.isShutDown = true;
  }

  protected static int readPort(String portVal,int defaultPort) {
    try {
      return Integer.parseInt(portVal);
    } catch (NumberFormatException nfe) {
      return defaultPort;
    }
  }
}
