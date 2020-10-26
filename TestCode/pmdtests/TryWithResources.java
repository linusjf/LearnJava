package pmdtests;

import java.io.IOException;
import java.io.InputStream;

public class TryWithResources {
  public void run() {

    try {
      InputStream in = openInputStream();
      int i = in.read();
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public InputStream openInputStream() {
    return null;
  }
}
