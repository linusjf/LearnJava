package bridge;

/**
 * Describe class <code>WindowsFileDownloadImplementor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class WindowsFileDownloadImplementor implements FileDownloadImplementor {

  @Override
  public Object downloadFile(String path) {
    return new Object();
  }

  @Override
  public boolean storeFile(Object object) {
    System.out.println("File downloaded successfully in WINDOWS !!");
    return true;
  }
}
