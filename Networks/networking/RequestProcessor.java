package networking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Logger;
import logging.FormatLogger;

public class RequestProcessor implements Runnable {
  private static final FormatLogger LOGGER = new FormatLogger(
      Logger.getLogger(RequestProcessor.class.getCanonicalName()));
  private File rootDirectory;
  private String indexFileName = "index.html";
  private Socket connection;

  public RequestProcessor(File rootDirectory,
                          String indexFileName,
                          Socket connection) {
    if (rootDirectory.isFile()) {
      throw new IllegalArgumentException(
          "rootDirectory must be a directory, not a file");
    }
    this.rootDirectory = rootDirectory;
    try {
      this.rootDirectory = rootDirectory.getCanonicalFile();
    } catch (IOException ex) {
      LOGGER.info("Error getting canonical root directory: %s",
                  ex.getMessage());
    }
    if (indexFileName != null)
      this.indexFileName = indexFileName;
    this.connection = connection;
  }

  @Override
  public void run() {
    try {
      OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
      Writer out = new OutputStreamWriter(raw);
      Reader in = new InputStreamReader(
          new BufferedInputStream(connection.getInputStream()), "US-ASCII");
      StringBuilder requestLine = new StringBuilder();
      while (true) {
        int c = in.read();
        if (c == '\r' || c == '\n')
          break;
        requestLine.append((char)c);
      }
      String get = requestLine.toString();
      LOGGER.info("%s %s", connection.getRemoteSocketAddress(), get);

      String[] tokens = get.split("\\s+");
      String method = tokens[0];
      if ("GET".equals(method)) {
        handleGet(tokens, raw, out);
      } else {  // method does not equal "GET"
        String version = tokens[2];
        String body =
            new StringBuilder("<HTML>\r\n")
                .append("<HEAD><TITLE>Not Implemented</TITLE>\r\n")
                .append("</HEAD>\r\n")
                .append("<BODY>")
                .append("<H1>HTTP Error 501: Not Implemented</H1>\r\n")
                .append("</BODY></HTML>\r\n")
                .toString();
        if (version.startsWith("HTTP/")) {  // send a MIME header
          sendHeader(out,
                     "HTTP/1.0 501 Not Implemented",
                     "text/html; charset=utf-8",
                     body.length());
        }
        out.write(body);
        out.flush();
        LOGGER.info("Method not supported: %s %s",
                    connection.getRemoteSocketAddress(),
                    get);
      }
    } catch (IOException ex) {
      LOGGER.warning("Error talking to %s: %s",
                     connection.getRemoteSocketAddress(),
                     ex.getMessage());
    } finally {
      try {
        connection.close();
      } catch (IOException ex) {
        LOGGER.warning("Error closing connection: ", ex.getMessage());
      }
    }
  }

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  private void handleGet(String[] tokens, OutputStream raw, Writer out)
      throws IOException {
    String version = "";
    String fileName = tokens[1];
    if (fileName.endsWith("/"))
      fileName = fileName.concat(indexFileName);
    String contentType =
        URLConnection.getFileNameMap().getContentTypeFor(fileName);
    if (tokens.length > 2) {
      version = tokens[2];
    }
    File theFile =
        new File(rootDirectory, fileName.substring(1, fileName.length()));
    if (theFile.canRead()
        // Don't let clients outside the document root
        && theFile.getCanonicalPath().startsWith(rootDirectory.getPath())) {
      byte[] theData = Files.readAllBytes(theFile.toPath());
      if (version.startsWith("HTTP/")) {  // send a MIME header
        sendHeader(out, "HTTP/1.0 200 OK", contentType, theData.length);
      }
      // send the file; it may be an image or other binary data
      // so use the underlying output stream
      // instead of the writer
      raw.write(theData);
      raw.flush();
    } else {  // can't find the file
      String body = new StringBuilder("<HTML>\r\n")
                        .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                        .append("</HEAD>\r\n")
                        .append("<BODY>")
                        .append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
                        .append("</BODY></HTML>\r\n")
                        .toString();
      if (version.startsWith("HTTP/")) {  // send a MIME header
        sendHeader(out,
                   "HTTP/1.0 404 File Not Found",
                   "text/html; charset=utf-8",
                   body.length());
      }
      out.write(body);
      out.flush();
    }
  }

  private void sendHeader(Writer out,
                          String responseCode,
                          String contentType,
                          int length) throws IOException {
    out.write(responseCode + "\r\n");
    Date now = new Date();
    out.write("Date: " + now + "\r\n");
    out.write("Server: JHttp 2.0\r\n");
    out.write("Content-length: " + length + "\r\n");
    out.write("Content-type: " + contentType + "\r\n\r\n");
    out.flush();
  }
}
