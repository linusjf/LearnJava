package com.javacodegeeks.snippets.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Scanner;

public class ProtectedUrlAccess {
  public static void main(String[] args) {
    try {
      // Sets the authenticator that will be used by the networking code
      // when a proxy or an HTTP server asks for authentication.
      Authenticator.setDefault(new CustomAuthenticator());

      double random = Math.random();

      String rnd = Double.valueOf(random).toString();

      URL url =
          new URL(
              "http://www.httpwatch.com/httpgallery/authentication/authenticatedimage/default.aspx?"
                  + rnd);

      // read text returned by server
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

      String line;
      while ((line = in.readLine()) != null) {
        System.out.println(line);
      }
      in.close();

    } catch (MalformedURLException e) {
      System.out.println("Malformed URL: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("I/O Error: " + e.getMessage());
    }
  }

  public static class CustomAuthenticator extends Authenticator {
    // Called when password authorization is needed
    protected PasswordAuthentication getPasswordAuthentication() {
      // Get information about the request
      String prompt = getRequestingPrompt();
      String hostname = getRequestingHost();
      InetAddress ipaddr = getRequestingSite();
      int port = getRequestingPort();

      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter username (use httpwatch): ");

      String username = scanner.nextLine();

      System.out.println("Enter password : ");

      String password = scanner.nextLine();

      // Return the information (a data holder that is used by Authenticator)
      return new PasswordAuthentication(username, password.toCharArray());
    }
  }
}
