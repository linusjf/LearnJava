package stefano.lupo;

import java.io.File;

@SuppressWarnings("PMD.LawOfDemeter")
public final class RequestHandlerUtils {
  private RequestHandlerUtils() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  static String extractURL(String requestString) {
    // remove request type and space
    System.out.println("Request = " + requestString);
    String urlString = requestString.substring(requestString.indexOf(' ') + 1);

    // Remove everything past next space
    urlString = urlString.substring(0, urlString.indexOf(' '));

    // Prepend http:// if necessary to create correct URL
    return urlString.startsWith("http") ? urlString : "http://" + urlString;
  }

  static String computeLogicalFilePrefix(String urlString) {
    int fileExtensionIndex = urlString.lastIndexOf('.');

    // Get the initial file name
    String fileName = urlString.substring(0, fileExtensionIndex);

    // Trim off http://www. as no need for it in file name
    fileName = fileName.substring(fileName.indexOf('.') + 1);

    // Remove any illegal characters from file name
    fileName = fileName.replace("/", "__");
    fileName = fileName.replace('.', '_');
    return fileName;
  }

  static String computeLogicalFileExtension(String urlString) {
    int fileExtensionIndex = urlString.lastIndexOf('.');
    String fileExtension;

    // Get the type of file
    fileExtension = urlString.substring(fileExtensionIndex, urlString.length());

    // Trailing / result in index.html of that directory being fetched
    if (fileExtension.contains("/")) {
      fileExtension = fileExtension.replace("/", "__");
      fileExtension = fileExtension.replace('.', '_');
      fileExtension = fileExtension.concat(".html");
    }
    return fileExtension;
  }

  public static String extractRequest(String requestString) {
    // Get the Request type
    return requestString.substring(0, requestString.indexOf(' '));
  }

  public static String getFileExtension(File file) {
    return file.getName().substring(file.getName().lastIndexOf('.'));
  }
}
