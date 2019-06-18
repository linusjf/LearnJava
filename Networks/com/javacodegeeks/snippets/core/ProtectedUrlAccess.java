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
import java.util.Scanner;

public final class ProtectedUrlAccess {

  private ProtectedUrlAccess() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {
      // Sets the authenticator that will be used by the networking code
      // when a proxy or an HTTP server asks for authentication.
      Authenticator.setDefault(new CustomAuthenticator());

      double random = Math.random();

      URL url =
          new URL(
              "http://www.httpwatch.com/httpgallery/authentication/authenticatedimage/default.aspx?"
                  + random);
      byte[] b = new byte[1];

      DataInputStream di = new DataInputStream(url.openStream());
      OutputStream fo = Files.newOutputStream(Paths.get(random + ".gif"));
      while (-1 != di.read(b, 0, 1)) fo.write(b, 0, 1);
      di.close();
      fo.close();
      System.out.println("Saved url as " + random + ".gif");
    } catch (MalformedURLException e) {
      System.out.println("Malformed URL: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("I/O Error: " + e.getMessage());
    }
  }

  public static class CustomAuthenticator extends Authenticator {
    // Called when password authorization is needed
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
      // Get information about the request
      String prompt = getRequestingPrompt();
      String hostname = getRequestingHost();
      InetAddress ipaddr = getRequestingSite();
      int port = getRequestingPort();

      Scanner scanner = new Scanner(System.in);
      System.out.println("Hostname: " + hostname);
      System.out.println("Ip Address: " + ipaddr);
      System.out.println("Port: " + port);
      System.out.println("Prompt: " + prompt);

      System.out.println("Enter username (use httpwatch): ");

      String username = scanner.nextLine();

      System.out.println("Enter password : ");

      String password = scanner.nextLine();

      // Return the information (a data holder that is used by Authenticator)
      return new PasswordAuthentication(username, password.toCharArray());
    }
  }
}
