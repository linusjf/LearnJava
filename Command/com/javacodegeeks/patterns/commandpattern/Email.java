package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

public class Email {
  private static final Logger LOGGER = Logger.getLogger(Email.class.getName());

  public void sendEmail() {
    LOGGER.info("Sending email.......");
  }
}
