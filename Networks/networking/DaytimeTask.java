package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

public class DaytimeTask implements Runnable, Callable<Void> {
  private static final String UTF_8 = StandardCharsets.UTF_8.name();
  private final Socket connection;

  DaytimeTask(Socket connection) {
    this.connection = connection;
  }

  @Override
  public Void call() {
    run();
    return null;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    try (Writer out = new OutputStreamWriter(connection.getOutputStream(), UTF_8);
        connection; ) {
      Date now = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss Z", Locale.getDefault());
      out.write(ProcessHandle.current().pid() + " " + format.format(now) + "\\r\\n");
      out.flush();
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
