package com.javacodegeeks.snippets.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ProtectedUrlAccess {
  private ProtectedUrlAccess() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
  public static void main(String[] args) {
    try {
      // Sets the authenticator that will be used by the networking code
      // when a proxy or an HTTP server asks for authentication.
      Authenticator.setDefault(args.length > 0
                                   ? new CustomAuthenticator(args[0])
                                   : new CustomAuthenticator());

      double random = Math.random();

      URL url = new URL(
          "http://www.httpwatch.com/httpgallery/authentication/authenticatedimage/default.aspx?"
          + random);
      byte[] b = new byte[1];
      try (DataInputStream di = new DataInputStream(url.openStream());
           OutputStream fo =
               Files.newOutputStream(Paths.get(random + ".gif"))) {
        while (-1 != di.read(b, 0, 1))
          fo.write(b, 0, 1);
        System.out.println("Saved url as " + random + ".gif");
      }
    } catch (MalformedURLException e) {
      System.out.println("Malformed URL: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("I/O Error: " + e.getMessage());
    }
  }

  public static class CustomAuthenticator extends Authenticator {
    private final String password;

    public CustomAuthenticator(String randomString) {
      this.password = randomString;
    }

    public CustomAuthenticator() {
      this.password = String.valueOf(Math.random());
    }

    // Called when password authorization is needed
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
      // Get information about the request
      String prompt = getRequestingPrompt();
      String hostname = getRequestingHost();
      InetAddress ipaddr = getRequestingSite();
      int port = getRequestingPort();

      System.out.println("Hostname: " + hostname);
      System.out.println("Ip Address: " + ipaddr);
      System.out.println("Port: " + port);
      System.out.println("Prompt: " + prompt);

      String username = "httpwatch";

      // Return the information (a data holder that is used by Authenticator)
      return new PasswordAuthentication(username, password.toCharArray());
    }
  }
}
