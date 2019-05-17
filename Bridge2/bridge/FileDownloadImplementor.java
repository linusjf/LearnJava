package bridge;
/**
 * Describe interface <code>FileDownloadImplementor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public interface FileDownloadImplementor {
  /**
   * Describe <code>downloadFile</code> method here.
   *
   * @param path a <code>String</code> value
   * @return an <code>Object</code> value
   */
  public Object downloadFile(String path);

  /**
   * Describe <code>storeFile</code> method here.
   *
   * @param object an <code>Object</code> value
   * @return a <code>boolean</code> value
   */
  public boolean storeFile(Object object);
}
