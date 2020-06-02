package bridge;

import java.util.logging.Logger;

/**
 * Describe class <code>Client</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum Client {
  ;
  private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    String os = "linux";
    FileDownloaderAbstraction downloader;

    switch (os) {
      case "windows":
        downloader = new FileDownloaderAbstractionImpl(
            new WindowsFileDownloadImplementor());
        break;
      case "linux":
        downloader = new FileDownloaderAbstractionImpl(
            new LinuxFileDownloadImplementor());
        break;
      default:
        LOGGER.severe("OS not supported !!");
        throw new AssertionError("OS not supported !!");
    }

    Object fileContent = downloader.download("some path");
    downloader.store(fileContent);
  }
}
