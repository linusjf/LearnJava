package stefano.lupo;

/*
 *  Student:    Stefano Lupo
 *  Student No: 14334933
 *  Degree:     JS Computer Engineering
 *  Course:     3D3 Computer Networks
 *  Date:       02/04/2017
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * The Proxy creates a Server Socket which will wait for connections on the
 * specified port. Once a connection arrives and a socket is accepted, the Proxy
 * creates a RequestHandler object on a new thread and passes the socket to it
 * to be handled. This allows the Proxy to continue accept further connections
 * while others are being handled.
 *
 * <p>The Proxy class is also responsible for providing the dynamic management
 * of the proxy through the console and is run on a separate thread in order to
 * not interrupt the acceptance of socket connections. This allows the
 * administrator to dynamically block web sites in real time.
 *
 * <p>The Proxy server is also responsible for maintaining cached copies of the
 * any websites that are requested by clients and this includes the HTML markup,
 * images, css and js files associated with each webpage.
 *
 * <p>Upon closing the proxy server, the HashMaps which hold cached items and
 * blocked sites are serialized and written to a file and are loaded back in
 * when the proxy is started once more, meaning that cached and blocked sites
 * are maintained.
 */
public class Proxy implements Runnable {
  private ServerSocket serverSocket;

  /** Semaphore for Proxy and Consolee Management System. */
  @SuppressWarnings("checkstyle:IllegalToken")
  private volatile boolean running = true;  // NOPMD

  /**
   * Data structure for constant order lookup of cache items. Key: URL of
   * page/image requested. Value: File in storage associated with this key.
   */
  static Map<String, File> cache;

  /**
   * Data structure for constant order lookup of blocked sites. Key: URL of
   * page/image requested. Value: URL of page/image requested.
   */
  static Map<String, String> blockedSites;

  /**
   * ArrayList of threads that are currently running and servicing requests.
   * This list is required in order to join all threads on closing of server.
   */
  static List<Thread> servicingThreads;

  static {
    // Load in hash map containing previously cached sites and blocked Sites
    cache = new HashMap<>();
    blockedSites = new HashMap<>();
    // Create array list to hold servicing threads
    servicingThreads = new ArrayList<>();
    loadCaches();
  }

  @SuppressWarnings("unchecked")
  private static void loadCaches() {
    try {
      // Load in cached sites from file
      File cachedSites = new File("cachedSites.txt");
      if (!cachedSites.createNewFile()) {
        InputStream fileInputStream =
            Files.newInputStream(Paths.get(cachedSites.getAbsolutePath()));
        ObjectInputStream objectInputStream =
            new ObjectInputStream(fileInputStream);

        cache = (HashMap<String, File>)objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
      }

      // Load in blocked sites from file
      File blockedSitesTxtFile = new File("blockedSites.txt");
      if (!blockedSitesTxtFile.createNewFile()) {
        InputStream fileInputStream = Files.newInputStream(
            Paths.get(blockedSitesTxtFile.getAbsolutePath()));
        ObjectInputStream objectInputStream =
            new ObjectInputStream(fileInputStream);
        blockedSites = (HashMap<String, String>)objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
      }
    } catch (IOException e) {
      System.out.println("Error loading previously cached sites file :"
                         + e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println(
          "Class not found loading in previously cached sites file : "
          + e.getMessage());
    }
  }

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("checkstyle:magicnumber")
  public static void main(String[] args) {
    // Create an instance of Proxy and begin listening for connections
    Proxy myProxy = new Proxy(8085);
    myProxy.listen();
  }

  /**
   * Create the Proxy Server.
   *
   * @param port Port number to run proxy server from.
   */
  public Proxy(int port) {
    // Start dynamic manager on a separate thread.
    new Thread(this).start();  // Starts overriden run() method at bottom

    try {
      // Create the Server Socket for the Proxy
      serverSocket = new ServerSocket(port);
      // Set the timeout
      // serverSocket.setSoTimeout(100000);
      // debug
      System.out.println("Waiting for client on port "
                         + serverSocket.getLocalPort() + "..");
      running = true;
    } catch (SocketException se) {
      System.out.println("Socket Exception when connecting to client "
                         + se.getMessage());
    } catch (SocketTimeoutException ste) {
      System.out.println("Timeout occured while connecting to client "
                         + ste.getMessage());
    } catch (IOException io) {
      System.out.println("IO exception when connecting to client "
                         + io.getMessage());
    }
  }

  /**
   * Listens to port and accepts new socket connections. Creates a new thread to
   * handle the request and passes it the socket connection and continues
   * listening.
   */
  @SuppressWarnings("checkstyle:magicnumber")
  public void listen() {
    while (running) {
      try {
        // serverSocket.accpet() Blocks until a connection is made
        Socket socket = serverSocket.accept();
        socket.setSoTimeout(60 * 1000);
        // Create new Thread and pass it Runnable RequestHandler
        Thread thread = new Thread(new RequestHandler(socket));
        // Key a reference to each thread so they can be joined later if
        // necessary
        servicingThreads.add(thread);
        thread.start();
      } catch (SocketException e) {
        // Socket exception is triggered by management system to shut down the
        // proxy
        System.out.println("Socket error : " + e.getMessage());
      } catch (IOException e) {
        System.out.println("IO error : " + e.getMessage());
      }
    }
  }

  /**
   * Saves the blocked and cached sites to a file so they can be re loaded at a
   * later time. Also joins all of the RequestHandler threads currently
   * servicing requests.
   */
  private void closeServer() {
    System.out.println("\nClosing Server..");
    running = false;
    try {
      OutputStream fileOutputStream =
          Files.newOutputStream(Paths.get("cachedSites.txt"));
      ObjectOutputStream objectOutputStream =
          new ObjectOutputStream(fileOutputStream);

      objectOutputStream.writeObject(cache);
      objectOutputStream.close();
      fileOutputStream.close();
      System.out.println("Cached Sites written");

      OutputStream fileOutputStream2 =
          Files.newOutputStream(Paths.get("blockedSites.txt"));
      ObjectOutputStream objectOutputStream2 =
          new ObjectOutputStream(fileOutputStream2);
      objectOutputStream2.writeObject(blockedSites);
      objectOutputStream2.close();
      fileOutputStream2.close();
      System.out.println("Blocked Site list saved");
      try {
        // Close all servicing threads
        for (Thread thread : servicingThreads) {
          if (thread.isAlive()) {
            System.out.print("Waiting on " + thread.getId() + " to close..");
            thread.join();
            System.out.println(" closed");
          }
        }
      } catch (InterruptedException e) {
        System.err.println(e.getMessage());
      }

    } catch (IOException e) {
      System.out.println("Error saving cache/blocked sites " + e.getMessage());
    }
    // Close Server Socket
    try {
      System.out.println("Terminating Connection");
      serverSocket.close();
    } catch (IOException e) {
      System.out.println("Exception closing proxy's server socket "
                         + e.getMessage());
    }
  }

  /**
   * Looks for File in cache.
   *
   * @param url of requested file
   * @return File if file is cached, null otherwise
   */
  public static File getCachedPage(String url) {
    return cache.get(url);
  }

  /**
   * Adds a new page to the cache.
   *
   * @param urlString URL of webpage to cache
   * @param fileToCache File Object pointing to File put in cache
   */
  public static void addCachedPage(String urlString, File fileToCache) {
    cache.put(urlString, fileToCache);
  }

  /**
   * Check if a URL is blocked by the proxy.
   *
   * @param url URL to check
   * @return true if URL is blocked, false otherwise
   */
  public static boolean isBlocked(String url) {
    return blockedSites.containsKey(url);
  }

  /**
   * Creates a management interface which can dynamically update the proxy
   * configurations blocked : Lists currently blocked sites cached : Lists
   * currently cached sites close : Closes the proxy server * : Adds * to the
   * list of blocked sites.
   */
  @Override
  public void run() {
    Scanner scanner = new Scanner(System.in);
    String command = "";
    System.out.println("Enter new site to block, or type "
                       + "\"blocked\" to see blocked sites, "
                       + "\"cached\" to see cached sites, or "
                       + "\"close\" to close server.");
    while (running) {
      if (scanner.hasNext()) {
        command = scanner.nextLine();
        if ("blocked".equals(command.toLowerCase(Locale.getDefault()))) {
          System.out.println("\nCurrently Blocked Sites");
          for (String key : blockedSites.keySet()) {
            System.out.println(key);
          }
          System.out.println();
        } else if ("cached".equals(command.toLowerCase(Locale.getDefault()))) {
          System.out.println("\nCurrently Cached Sites");
          for (String key : cache.keySet()) {
            System.out.println(key);
          }
          System.out.println();
        } else if ("close".equals(command.toLowerCase(Locale.getDefault()))) {
          running = false;
          closeServer();
        } else {
          blockedSites.put(command, command);
          System.out.println("\n" + command + " blocked successfully \n");
        }
      }
    }
    scanner.close();
  }
}
