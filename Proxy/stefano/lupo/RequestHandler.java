package stefano.lupo;

import static stefano.lupo.RequestHandlerUtils.*;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

/**
 * Describe class <code>RequestHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class RequestHandler implements Runnable {
  private static final String IMG_REGEX = "\\.(png|jpg|jpeg|gif)";

  private static final String PROXY_AGENT_STR =
      "Proxy-agent: ProxyServer/1.0" + System.lineSeparator();

  private static final String HTTP_OK = "HTTP/1.0 200 OK" + System.lineSeparator();

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  /** Socket connected to client passed by Proxy server. */
  final Socket clientSocket;

  /**
   * Creates a RequestHandler object capable of servicing HTTP(S) GET requests.
   *
   * @param clientSocket socket connected to the client
   */
  public RequestHandler(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  /**
   * This method is called when user requests a page that is blocked by the proxy. Sends an access
   * forbidden message back to the client
   */
  private void blockedSiteRequested() {
    try (BufferedWriter bufferedWriter =
        new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), UTF_8))) {
      String line =
          "HTTP/1.0 403 Access Forbidden \n"
              + "User-Agent: ProxyServer/1.0\n"
              + System.lineSeparator();
      bufferedWriter.write(line);
      bufferedWriter.flush();
    } catch (IOException e) {
      System.out.println("Error writing to client when requested a blocked site" + e.getMessage());
    }
  }

  /**
   * Reads and examines the requestString and calls the appropriate method based on the request
   * type.
   */
  @SuppressWarnings("PMD.EmptyWhileStmt")
  @Override
  public void run() {
    // Get Request from client
    try (BufferedReader br =
            new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream(), UTF_8));
        BufferedWriter bw =
            new BufferedWriter(
                new OutputStreamWriter(this.clientSocket.getOutputStream(), UTF_8)); ) {
      String requestString;
      while ((requestString = br.readLine()) == null)
        ;

      // Parse out URL
      System.out.println("Request Received " + requestString);

      // remove request type and space
      String urlString = extractURL(requestString);

      // Check if site is blocked
      if (Proxy.isBlocked(urlString)) {
        System.out.println("Blocked site requested : " + urlString);
        blockedSiteRequested();
        return;
      }

      // Get the Request type
      final String request = extractRequest(requestString);

      // Check request type
      if ("CONNECT".equals(request)) {
        System.out.println("HTTPS Request for : " + urlString + "\n");
        handleHTTPSRequest(urlString, bw, br);
      } else handleForCaching(urlString, bw);
    } catch (IOException ioe) {
      System.out.println("IO error : " + ioe.getMessage());
    }
  }

  private void handleForCaching(String urlString, BufferedWriter bw) {
    // Check if we have a cached copy
    File file = Proxy.getCachedPage(urlString);
    if (file != null) {
      System.out.println("Cached Copy found for : " + urlString + "\n");
      sendCachedPageToClient(file, bw);
      return;
    }
    System.out.println("HTTP GET for : " + urlString + "\n");
    sendNonCachedToClient(urlString, bw);
  }

  /**
   * Sends the specified cached file to the client.
   *
   * @param cachedFile The file to be sent (can be image/text)
   */
  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  private void sendCachedPageToClient(File cachedFile, BufferedWriter proxyToClientBw) {
    // Read from File containing cached web page
    try {
      // If file is an image write data to client using buffered image.
      String fileExtension = getFileExtension(cachedFile);

      // Response that will be sent to the server
      if (fileExtension.matches(IMG_REGEX))
        handleImageFile(cachedFile, proxyToClientBw, fileExtension);
      else handleAllOtherFiles(cachedFile, proxyToClientBw);

    } catch (IOException e) {
      System.out.println("Error Sending Cached file to client " + e.getMessage());
    }
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private void handleAllOtherFiles(File cachedFile, BufferedWriter proxyToClientBw)
      throws IOException {
    try (BufferedReader cachedFileBufferedReader =
        Files.newBufferedReader(Paths.get(cachedFile.getAbsolutePath()))) {
      String response = HTTP_OK + PROXY_AGENT_STR + System.lineSeparator();
      proxyToClientBw.write(response);
      proxyToClientBw.flush();

      String line;
      while ((line = cachedFileBufferedReader.readLine()) != null) proxyToClientBw.write(line);
      proxyToClientBw.flush();
    }
  }

  private void handleImageFile(
      File cachedFile, BufferedWriter proxyToClientBw, String fileExtension) throws IOException {
    BufferedImage image = ImageIO.read(cachedFile);

    if (image == null) {
      System.out.println("Image " + cachedFile.getName() + " was null");
      String response = "HTTP/1.0 404 NOT FOUND \n" + PROXY_AGENT_STR + System.lineSeparator();
      proxyToClientBw.write(response);
      proxyToClientBw.flush();
    } else {
      String response = HTTP_OK + PROXY_AGENT_STR + System.lineSeparator();
      proxyToClientBw.write(response);
      proxyToClientBw.flush();
      ImageIO.write(image, fileExtension.substring(1), clientSocket.getOutputStream());
    }
  }

  private File getCacheFile(String fileName) {
    File fileToCache = new File("cached/" + fileName);
    try {
      if (!fileToCache.exists()) {
        String parent = fileToCache.getParent();
        File parentPath = new File(parent);
        if (!(parentPath.mkdirs() && fileToCache.createNewFile()))
          throw new AssertionError(
              "Unable to create parent directories : " + parentPath + " or file: " + fileToCache);
      }
    } catch (IOException | SecurityException e) {
      System.err.println("Error creating cache file: " + e.getMessage());
    }
    return fileToCache;
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  private void sendErrorMsgToClient(Writer proxyToClientBw) throws IOException {
    String error = "HTTP/1.0 404 NOT FOUND\n" + PROXY_AGENT_STR + System.lineSeparator();
    proxyToClientBw.write(error);
    proxyToClientBw.flush();
  }

  /**
   * Sends the contents of the file specified by the urlString to the client.
   *
   * @param urlString URL ofthe file requested
   */
  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  private void sendNonCachedToClient(String urlString, BufferedWriter proxyToClientBw) {
    // Compute a logical file name as per schema
    // This allows the files on stored on disk to resemble that of the URL it
    // was taken from
    String fileExtension = computeLogicalFileExtension(urlString);

    File fileToCache = getCacheFile(urlString);
    // Attempt to create File to cache to
    try (BufferedWriter fileToCacheBW =
        Files.newBufferedWriter(Paths.get(fileToCache.getAbsolutePath()))) {
      // Check if file is an image
      if (fileExtension.matches(IMG_REGEX)) {
        // Create the URL
        URL remoteURL = new URL(urlString);
        BufferedImage image = ImageIO.read(remoteURL);

        if (image == null) {
          String fileName = computeLogicalFilePrefix(urlString) + fileExtension;
          System.out.println(
              "Sending 404 to client as image wasn't received from server" + fileName);
          sendErrorMsgToClient(proxyToClientBw);
          return;
        }

        // Cache the image to disk
        ImageIO.write(image, fileExtension.substring(1), fileToCache);

        // Send response code to client
        String line = HTTP_OK + PROXY_AGENT_STR + System.lineSeparator();
        proxyToClientBw.write(line);
        proxyToClientBw.flush();

        // Send them the image data
        ImageIO.write(image, fileExtension.substring(1), clientSocket.getOutputStream());
      } else {
        // Create the URL
        URL remoteURL = new URL(urlString);

        // Create a connection to remote server
        HttpURLConnection proxyToServerCon = openConnection(remoteURL);

        // Create Buffered Reader from remote Server
        try (BufferedReader proxyToServerBR =
            new BufferedReader(new InputStreamReader(proxyToServerCon.getInputStream(), UTF_8))) {
          // Send success code to client
          String line = HTTP_OK + PROXY_AGENT_STR + System.lineSeparator();
          proxyToClientBw.write(line);

          // Read from input stream between proxy and remote server
          while ((line = proxyToServerBR.readLine()) != null) {
            // Send on data to client
            proxyToClientBw.write(line);

            // Write to our cached copy of the file
            if (fileToCache.exists()) fileToCacheBW.write(line);
          }

          // Ensure all data is sent by this point
          proxyToClientBw.flush();
        }
      }

      if (fileToCache.exists()) {
        // Ensure data written and add to our cached hash maps
        fileToCacheBW.flush();
        Proxy.addCachedPage(urlString, fileToCache);
      }
    } catch (IOException e) {
      System.err.println("Error sending " + urlString + " to client : " + e.getMessage());
    }
  }

  private HttpURLConnection openConnection(URL remoteURL) throws IOException {
    HttpURLConnection proxyToServerCon = (HttpURLConnection) remoteURL.openConnection();
    setProperties(proxyToServerCon);
    return proxyToServerCon;
  }

  private void setProperties(HttpURLConnection conn) {
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    conn.setRequestProperty("Content-Language", "en-US");
    conn.setUseCaches(false);
    conn.setDoOutput(true);
  }

  /**
   * Handles HTTPS requests between client and remote server.
   *
   * @param urlString desired file to be transmitted over https
   */
  @SuppressWarnings({
    "checkstyle:abbreviationaswordinname",
    "checkstyle:magicnumber",
    "PMD.DataflowAnomalyAnalysis",
    "PMD.LawOfDemeter"
  })
  private void handleHTTPSRequest(
      String urlString, BufferedWriter proxyToClientBw, BufferedReader proxyToClientBr) {
    // Extract the URL and port of remote
    String url = urlString.substring(7);
    String[] pieces = url.split(":");
    url = pieces[0];
    int port = Integer.parseInt(pieces[1]);

    try {
      // Only first line of HTTPS request has been read at this point (CONNECT
      // *) Read (and throw away) the rest of the initial data on the stream
      for (int i = 0; i < 5; i++) proxyToClientBr.readLine();

      // Get actual IP associated with this URL through DNS
      InetAddress address = InetAddress.getByName(url);

      // Open a socket to the remote server
      try (Socket proxyToServerSocket = new Socket(address, port); ) {
        proxyToServerSocket.setSoTimeout(60 * 1000);

        // Send Connection established to the client
        String line =
            "HTTP/1.0 200 Connection established\r\n" + PROXY_AGENT_STR + System.lineSeparator();
        proxyToClientBw.write(line);
        proxyToClientBw.flush();

        // Create a new thread to listen to client and transmit to server
        ClientToServerHttpsTransmit clientToServerHttps =
            new ClientToServerHttpsTransmit(
                clientSocket.getInputStream(), proxyToServerSocket.getOutputStream());

        Thread httpsClientToServer = new Thread(clientToServerHttps);
        httpsClientToServer.start();

        // Listen to remote server and relay to client
        byte[] buffer = new byte[4096];
        int read = 0;
        while (read >= 0) {
          read = proxyToServerSocket.getInputStream().read(buffer);
          if (read > 0) {
            clientSocket.getOutputStream().write(buffer, 0, read);
            if (proxyToServerSocket.getInputStream().available() == 0)
              clientSocket.getOutputStream().flush();
          }
        }
      }

    } catch (SocketTimeoutException e) {
      String line =
          "HTTP/1.0 504 Timeout Occured after 10s\n"
              + "User-Agent: ProxyServer/1.0\n"
              + System.lineSeparator();
      try {
        proxyToClientBw.write(line);
        proxyToClientBw.flush();
      } catch (IOException ioe) {
        System.err.println(ioe.getMessage());
      }
    } catch (IOException e) {
      System.out.println("Error on HTTPS : " + urlString + e.getMessage());
    }
  }

  /**
   * Listen to data from client and transmits it to server. This is done on a separate thread as
   * must be done asynchronously to reading data from server and transmitting that data to the
   * client.
   */
  static class ClientToServerHttpsTransmit implements Runnable {
    InputStream proxyToClientIS;
    OutputStream proxyToServerOS;

    /**
     * Creates Object to Listen to Client and Transmit that data to the server.
     *
     * @param proxyToClientIS Stream that proxy uses to receive data from client
     * @param proxyToServerOS Stream that proxy uses to transmit data to remote server
     */
    ClientToServerHttpsTransmit(InputStream proxyToClientIS, OutputStream proxyToServerOS) {
      this.proxyToClientIS = proxyToClientIS;
      this.proxyToServerOS = proxyToServerOS;
    }

    @SuppressWarnings({"checkstyle:magicnumber", "PMD.DataflowAnomalyAnalysis"})
    @Override
    public void run() {
      try {
        // Read byte by byte from client and send directly to server
        byte[] buffer = new byte[4096];
        int read = 0;
        while (read >= 0) {
          read = proxyToClientIS.read(buffer);
          if (read > 0) {
            proxyToServerOS.write(buffer, 0, read);
            if (proxyToClientIS.available() == 0) proxyToServerOS.flush();
          }
        }
      } catch (SocketTimeoutException ste) {
        System.err.println("Socket time out : " + ste.getMessage());
      } catch (IOException e) {
        System.out.println("Proxy to client HTTPS read timed out" + e.getMessage());
      }
    }
  }
}
