package bridge;

/**
 * Describe class <code>Client</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum Client {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    String os = "linux";
    FileDownloaderAbstraction downloader = null;

    switch (os) {
      case "windows":
        downloader = new FileDownloaderAbstractionImpl(new WindowsFileDownloadImplementor());
        break;
      case "linux":
        downloader = new FileDownloaderAbstractionImpl(new LinuxFileDownloadImplementor());
        break;
      default:
        System.out.println("OS not supported !!");
        break;
    }

    Object fileContent = downloader.download("some path");
    downloader.store(fileContent);
  }
}
