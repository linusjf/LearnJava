package bridge;

import java.util.Objects;

/**
 * Describe class <code>FileDownloaderAbstractionImpl</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class FileDownloaderAbstractionImpl
    implements FileDownloaderAbstraction {
  private final FileDownloadImplementor provider;

  /**
   * Creates a new <code>FileDownloaderAbstractionImpl</code> instance.
   *
   * @param provider a <code>FileDownloadImplementor</code> value
   */
  public FileDownloaderAbstractionImpl(FileDownloadImplementor provider) {
    this.provider = provider;
  }

  @Override
  public String toString() {
   return getClass() + " : " +
     "Provider = " + provider;
  }
  
  @Override
  public boolean equals(Object o) {
   if (this == o)
     return true;
  if (o instanceof FileDownloaderAbstractionImpl) {
return provider.equals(((FileDownloaderAbstractionImpl)o).provider);
  }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(provider);
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
