package bridge;

/**
 * Describe class <code>FileDownloaderAbstractionImpl</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class FileDownloaderAbstractionImpl implements FileDownloaderAbstraction {
  private FileDownloadImplementor provider;

  /**
   * Creates a new <code>FileDownloaderAbstractionImpl</code> instance.
   *
   * @param provider a <code>FileDownloadImplementor</code> value
   */
  public FileDownloaderAbstractionImpl(FileDownloadImplementor provider) {
    super();
    this.provider = provider;
  }

  @Override
  public Object download(String path) {
    return provider.downloadFile(path);
  }

  @Override
  public boolean store(Object object) {
    return provider.storeFile(object);
  }
}
