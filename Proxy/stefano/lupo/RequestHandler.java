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
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
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

  /** Socket connected to client passed by Proxy server. */
  final Socket clientSocket;

  /** Read data client sends to proxy. */
  BufferedReader proxyToClientBr;

  /** Send data from proxy to client. */
  BufferedWriter proxyToClientBw;

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
        new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
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
  @Override
  public void run() {
    // Get Request from client
    String requestString;
    try (BufferedReader br =
            new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        BufferedWriter bw =
            new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream())); ) {
      proxyToClientBr = br;
      proxyToClientBw = bw;
      do {
        requestString = proxyToClientBr.readLine();
      } while (requestString == null);
      // Parse out URL
      System.out.println("Request Received " + requestString);

      if (requestString != null) {
        // remove request type and space
        String urlString = extractURL(requestString);
        // Check if site is blocked
        if (Proxy.isBlocked(urlString)) {
          System.out.println("Blocked site requested : " + urlString);
          blockedSiteRequested();
          return;
        }

        // Get the Request type
        final String request = requestString.substring(0, requestString.indexOf(' '));
        // Check request type
        if ("CONNECT".equals(request)) {
          System.out.println("HTTPS Request for : " + urlString + "\n");
          handleHTTPSRequest(urlString);
        } else {
          handleForCaching(urlString);
        }
      }
    } catch (IOException ioe) {
      System.out.println("IO error : " + ioe.getMessage());
    }
  }

  private void handleForCaching(String urlString) {
    // Check if we have a cached copy
    File file = Proxy.getCachedPage(urlString);
    if (file != null) {
      System.out.println("Cached Copy found for : " + urlString + "\n");
      sendCachedPageToClient(file);
      return;
    }
    System.out.println("HTTP GET for : " + urlString + "\n");
    sendNonCachedToClient(urlString);
  }

  /**
   * Sends the specified cached file to the client.
   *
   * @param cachedFile The file to be sent (can be image/text)
   */
  private void sendCachedPageToClient(File cachedFile) {
    // Read from File containing cached web page
    try {
      // If file is an image write data to client using buffered image.
      String fileExtension = cachedFile.getName().substring(cachedFile.getName().lastIndexOf('.'));

      // Response that will be sent to the server
      String response;
      if (fileExtension.matches(IMG_REGEX)) {
        // Read in image from storage
        BufferedImage image = ImageIO.read(cachedFile);

        if (image == null) {
          System.out.println("Image " + cachedFile.getName() + " was null");
          response = "HTTP/1.0 404 NOT FOUND \n" + PROXY_AGENT_STR + System.lineSeparator();
          proxyToClientBw.write(response);
          proxyToClientBw.flush();
        } else {
          response = HTTP_OK + PROXY_AGENT_STR + System.lineSeparator();
          proxyToClientBw.write(response);
          proxyToClientBw.flush();
          ImageIO.write(image, fileExtension.substring(1), clientSocket.getOutputStream());
        }
      } else {
        final BufferedReader cachedFileBufferedReader =
            Files.newBufferedReader(Paths.get(cachedFile.getAbsolutePath()));

        response = HTTP_OK + PROXY_AGENT_STR + System.lineSeparator();
        proxyToClientBw.write(response);
        proxyToClientBw.flush();

        String line;
        while ((line = cachedFileBufferedReader.readLine()) != null) {
          proxyToClientBw.write(line);
        }
        proxyToClientBw.flush();

        // Close resources
        if (cachedFileBufferedReader != null) {
          cachedFileBufferedReader.close();
        }
      }

      // Close Down Resources
      if (proxyToClientBw != null) {
        proxyToClientBw.close();
      }

    } catch (IOException e) {
      System.out.println("Error Sending Cached file to client " + e.getMessage());
    }
  }

  private File getCacheFile(String fileName) {
    File fileToCache = new File("cached/" + fileName);
    try {
      if (!fileToCache.exists()) {
        String parent = fileToCache.getParent();
        File parentPath = new File(parent);
        parentPath.mkdirs();
        fileToCache.createNewFile();
      }
    } catch (IOException | SecurityException e) {
      System.err.println("Error creating cache file: " + e.getMessage());
    }
    return fileToCache;
  }

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
  private void sendNonCachedToClient(String urlString) {
    // Compute a logical file name as per schema
    // This allows the files on stored on disk to resemble that of the URL it
    // was taken from
    String fileExtension = computeLogicalFileExtension(urlString);
    String fileName = computeLogicalFilePrefix(urlString) + fileExtension;
    // Attempt to create File to cache to
    boolean caching = true;
    File fileToCache = null;
    BufferedWriter fileToCacheBW = null;

    try {
      fileToCache = getCacheFile(urlString);
      caching = fileToCache.exists();
      // Create Buffered output stream to write to cached copy of file
      fileToCacheBW = Files.newBufferedWriter(Paths.get(fileToCache.getAbsolutePath()));

      // Check if file is an image
      if (fileExtension.matches(IMG_REGEX)) {
        // Create the URL
        URL remoteURL = new URL(urlString);
        BufferedImage image = ImageIO.read(remoteURL);

        if (image == null) {
          System.out.println(
              "Sending 404 to client as image wasn't received from server" + fileName);
          sendErrorMsgToClient(proxyToClientBw);
          closeResources(fileToCacheBW, proxyToClientBw);
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
        HttpURLConnection proxyToServerCon = (HttpURLConnection) remoteURL.openConnection();
        proxyToServerCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        proxyToServerCon.setRequestProperty("Content-Language", "en-US");
        proxyToServerCon.setUseCaches(false);
        proxyToServerCon.setDoOutput(true);

        // Create Buffered Reader from remote Server
        BufferedReader proxyToServerBR =
            new BufferedReader(new InputStreamReader(proxyToServerCon.getInputStream()));

        // Send success code to client
        String line = HTTP_OK + PROXY_AGENT_STR + System.lineSeparator();
        proxyToClientBw.write(line);

        // Read from input stream between proxy and remote server
        while ((line = proxyToServerBR.readLine()) != null) {
          // Send on data to client
          proxyToClientBw.write(line);

          // Write to our cached copy of the file
          if (caching) fileToCacheBW.write(line);
        }

        // Ensure all data is sent by this point
        proxyToClientBw.flush();

        // Close Down Resources
        if (proxyToServerBR != null) proxyToServerBR.close();
      }

      if (caching) {
        // Ensure data written and add to our cached hash maps
        fileToCacheBW.flush();
        Proxy.addCachedPage(urlString, fileToCache);
      }
      closeResources(fileToCacheBW, proxyToClientBw);
    } catch (IOException e) {
      System.err.println("Error sending " + urlString + " to client : " + e.getMessage());
    }
  }

  /**
   * Handles HTTPS requests between client and remote server.
   *
   * @param urlString desired file to be transmitted over https
   */
  @SuppressWarnings({"checkstyle:abbreviationaswordinname", "checkstyle:magicnumber"})
  private void handleHTTPSRequest(String urlString) {
    // Extract the URL and port of remote
    String url = urlString.substring(7);
    String[] pieces = url.split(":");
    url = pieces[0];
    int port = Integer.valueOf(pieces[1]);

    try {
      // Only first line of HTTPS request has been read at this point (CONNECT
      // *) Read (and throw away) the rest of the initial data on the stream
      for (int i = 0; i < 5; i++) proxyToClientBr.readLine();

      // Get actual IP associated with this URL through DNS
      InetAddress address = InetAddress.getByName(url);

      // Open a socket to the remote server
      Socket proxyToServerSocket = new Socket(address, port);
      proxyToServerSocket.setSoTimeout(60 * 1000);

      // Send Connection established to the client
      String line =
          "HTTP/1.0 200 Connection established\r\n" + PROXY_AGENT_STR + System.lineSeparator();
      proxyToClientBw.write(line);
      proxyToClientBw.flush();

      // Create a Buffered Writer betwen proxy and remote
      final BufferedWriter proxyToServerBW =
          new BufferedWriter(new OutputStreamWriter(proxyToServerSocket.getOutputStream()));

      // Create Buffered Reader from proxy and remote
      final BufferedReader proxyToServerBR =
          new BufferedReader(new InputStreamReader(proxyToServerSocket.getInputStream()));

      // Create a new thread to listen to client and transmit to server
      ClientToServerHttpsTransmit clientToServerHttps =
          new ClientToServerHttpsTransmit(
              clientSocket.getInputStream(), proxyToServerSocket.getOutputStream());

      Thread httpsClientToServer = new Thread(clientToServerHttps);
      httpsClientToServer.start();

      // Listen to remote server and relay to client
      byte[] buffer = new byte[4096];
      int read;
      do {
        read = proxyToServerSocket.getInputStream().read(buffer);
        if (read > 0) {
          clientSocket.getOutputStream().write(buffer, 0, read);
          if (proxyToServerSocket.getInputStream().available() == 0)
            clientSocket.getOutputStream().flush();
        }
      } while (read >= 0);
      closeResources(proxyToServerSocket, proxyToServerBR, proxyToServerBW, proxyToClientBw);
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

  private void closeResources(
      Socket proxyToServerSocket,
      Reader proxyToServerBR,
      Writer proxyToServerBW,
      Writer proxyToClientBw)
      throws IOException {
    // Close Down Resources
    if (proxyToServerSocket != null) proxyToServerSocket.close();

    if (proxyToServerBR != null) proxyToServerBR.close();

    if (proxyToServerBW != null) proxyToServerBW.close();

    if (proxyToClientBw != null) proxyToClientBw.close();
  }

  private void closeResources(Writer fileToCacheBW, Writer proxyToClientBw) throws IOException {
    // Close down resources
    if (fileToCacheBW != null) {
      fileToCacheBW.close();
    }

    if (proxyToClientBw != null) {
      proxyToClientBw.close();
    }
  }

  /**
   * Listen to data from client and transmits it to server. This is done on a separate thread as
   * must be done asynchronously to reading data from server and transmitting that data to the
   * client.
   */
  class ClientToServerHttpsTransmit implements Runnable {
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

    @Override
    @SuppressWarnings("checkstyle:magicnumber")
    public void run() {
      try {
        // Read byte by byte from client and send directly to server
        byte[] buffer = new byte[4096];
        int read;
        do {
          read = proxyToClientIS.read(buffer);
          if (read > 0) {
            proxyToServerOS.write(buffer, 0, read);
            if (proxyToClientIS.available() == 0) proxyToServerOS.flush();
          }
        } while (read >= 0);
      } catch (SocketTimeoutException ste) {
        System.err.println("Socket time out : " + ste.getMessage());
      } catch (IOException e) {
        System.out.println("Proxy to client HTTPS read timed out" + e.getMessage());
      }
    }
  }
}
