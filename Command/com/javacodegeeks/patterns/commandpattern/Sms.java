package com.javacodegeeks.patterns.commandpattern;

import java.util.logging.Logger;

@SuppressWarnings("PMD.ShortClassName")
public class Sms {
  private static final Logger LOGGER = Logger.getLogger(Sms.class.getName());

  public void sendSms() {
    LOGGER.info("Sending SMS...");
  }
}
