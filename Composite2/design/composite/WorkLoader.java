package design.composite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    try (InputStream input = new FileInputStream(fileName)) {
      // load a properties file
      properties.load(input);
    } catch (IOException exp) {
      exp.printStackTrace();
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
    List<Work> workList = new ArrayList<Work>();
    Set<Object> keys = properties.keySet();
    for (Object key : keys) {
      String workType = key.toString().substring("Calculate".length() + 1).toUpperCase();
      System.out.println(workType);
      String values = properties.getProperty(key.toString());
      System.out.println(values);
      Work work = new Work(Calculator.valueOf(workType), Arrays.asList(values.split(",")));
      workList.add(work);
    }
    return workList;
  }
}
