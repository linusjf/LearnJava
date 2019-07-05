package design.composite;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

/**
 * Describe class <code>WorkLoader</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class WorkLoader {
  protected Properties properties = new Properties();

  /**
   * Creates a new <code>WorkLoader</code> instance.
   *
   * @param fileName a <code>String</code> value
   */
  public WorkLoader(String fileName) {
    try (InputStream input = Files.newInputStream(Paths.get(fileName))) {
      // load a properties file
      properties.load(input);
    } catch (IOException exp) {
      System.err.println("Error reading properties file: " + exp.getMessage());
    }
  }

  /**
   * Describe <code>getProperties</code> method here.
   *
   * @return a <code>Properties</code> value
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * Describe <code>getWorkList</code> method here.
   *
   * @return a <code>List</code> object
   */
  public List<Work> getWorkList() {
    List<Work> workList = new ArrayList<>();
    Set<Object> keys = properties.keySet();
    for (Object key : keys) {
      String workType = key.toString()
                            .substring("Calculate".length() + 1)
                            .toUpperCase(Locale.getDefault());
      System.out.println(workType);
      String values = properties.getProperty(key.toString());
      System.out.println(values);
      addWorkToList(workList, workType, values);
    }
    return workList;
  }

  private void addWorkToList(List<Work> workList, String workType, String values) {
    workList.add(new Work(Calculator.valueOf(workType),
                          Arrays.asList(values.split(","))));
  }
}
