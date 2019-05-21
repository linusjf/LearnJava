package bridge;

/**
 * Describe interface <code>FileDownloaderAbstraction</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
interface FileDownloaderAbstraction {
  /**
   * Describe <code>download</code> method here.
   *
   * @param path a <code>String</code> value
   * @return an <code>Object</code> value
   */
  Object download(String path);

  /**
   * Describe <code>store</code> method here.
   *
   * @param object an <code>Object</code> value
   * @return a <code>boolean</code> value
   */
  boolean store(Object object);
}
