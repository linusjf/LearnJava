package logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class FormatLogger {
  private final Logger logger;

  public FormatLogger(Logger logger) {
    this.logger = logger;
  }

  public void severe(String formatter, Object... args) {
    log(Level.SEVERE, formatter, args);
  }

  public void info(String formatter, Object... args) {
    log(Level.INFO, formatter, args);
  }

  public void fine(String formatter, Object... args) {
    log(Level.FINE, formatter, args);
  }

  public void finer(String formatter, Object... args) {
    log(Level.FINER, formatter, args);
  }

  public void finest(String formatter, Object... args) {
    log(Level.FINEST, formatter, args);
  }

  public void warning(String formatter, Object... args) {
    log(Level.WARNING, formatter, args);
  }

  public void log(Level level, String formatter, Object... args) {
    if (logger.isLoggable(level)) {
      /*
       * Only now is the message constructed, and each "arg"
       * evaluated by having its toString() method invoked.
       */
      logger.log(level, String.format(formatter, args));
    }
  }
}
