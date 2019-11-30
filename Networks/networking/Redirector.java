package networking;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Logger;
import logging.FormatLogger;

public class Redirector {
  private static final FormatLogger LOGGER =
      new FormatLogger(Logger.getLogger("Redirector"));
  private final int port;
  private final String newSite;

  public Redirector(String newSite, int port) {
    this.port = port;
    this.newSite = newSite;
  }

  public void start() {
    try (ServerSocket server = new ServerSocket(port)) {
      LOGGER.info("Redirecting connections on port %d to %s",
                  server.getLocalPort(),
                  newSite);
      while (true) {
        try {
          Socket s = server.accept();
          Thread t = new RedirectThread(s);
          t.start();
        } catch (IOException ex) {
          LOGGER.warning("Exception accepting connection: %s", ex.getMessage());
        }
      }
    } catch (BindException ex) {
      LOGGER.severe("Could not start server: %s", ex.getMessage());
    } catch (IOException ex) {
      LOGGER.severe("Error opening server socket: %s", ex.getMessage());
    }
  }

  private static int getPort(String... args) {
    if (args.length > 1) {
      try {
        return Integer.parseInt(args[1]);
      } catch (NumberFormatException ex) {
        LOGGER.warning("Using port 80. Port cannot be parsed from %s", args[1]);
      }
    }
    return 80;
  }

  public static void main(String[] args) {
    String theSite;
    if (args.length > 0) {
      theSite = args[0];

      // trim trailing slash
      if (theSite.endsWith("/")) {
        theSite = theSite.substring(0, theSite.length() - 1);
      }
    } else {
      System.out.println("Usage: java Redirector http://www.newsite.com/ port");
      return;
    }
    int thePort = getPort(args);

    Redirector redirector = new Redirector(theSite, thePort);
    redirector.start();
  }

  private class RedirectThread extends Thread {
    private final Socket connection;

    RedirectThread(Socket s) {
      super();
      this.connection = s;
    }

    @Override
    public void run() {
      try (Writer out = new BufferedWriter(new OutputStreamWriter(
               connection.getOutputStream(), "US-ASCII"));
           Reader in = new InputStreamReader(
               new BufferedInputStream(connection.getInputStream()));) {
        // read the first line only; that's all we need
        StringBuilder request = new StringBuilder(80);
        while (true) {
          int c = in.read();
          if (c == '\r' || c == '\n' || c == -1)
            break;
          request.append((char)c);
        }
        String get = request.toString();
        String[] pieces = get.split("\\w*");
        String theFile = pieces[1];

        // If this is HTTP/1.0 or later send a MIME header
        if (get.indexOf("HTTP") != -1) {
          out.write("HTTP/1.0 302 FOUND\r\n");
          Date now = new Date();
          out.write("Date: " + now + "\r\n");
          out.write("Server: Redirector 1.1\r\n");
          out.write("Location: " + newSite + theFile + "\r\n");
          out.write("Content-type: text/html\r\n\r\n");
          out.flush();
        }

        // Not all browsers support redirection so we need to
        // produce HTML that says where the document has moved to.
        out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
        out.write("<BODY><H1>Document moved</H1>\r\n");
        out.write("The document " + theFile + " has moved to\r\n<A HREF=\""
                  + newSite + theFile + "\">" + newSite + theFile
                  + "</A>.\r\n Please update your bookmarks<P>");
        out.write("</BODY></HTML>\r\n");
        out.flush();
        LOGGER.info("Redirected %s", connection.getRemoteSocketAddress());
      } catch (IOException ex) {
        LOGGER.warning("Error talking to %s: %s",
                       connection.getRemoteSocketAddress(),
                       ex.getMessage());
      } finally {
        try {
          connection.close();
        } catch (IOException ex) {
          LOGGER.warning("Error closing %s: %s",
                         connection.getRemoteSocketAddress(),
                         ex.getMessage());
        }
      }
    }
  }
}
