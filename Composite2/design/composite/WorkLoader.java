package design.composite;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
/**
 * Describe class <code>WorkLoader</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class WorkLoader {
  private static final Logger LOGGER = Logger.getLogger(WorkLoader.class.getName());
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
      LOGGER.severe(() -> String.format("Error reading properties file: %s", exp.getMessage()));
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
  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public List<Work> getWorkList() {
    List<Work> workList = new ArrayList<>();
    Set<Object> keys = properties.keySet();
    for (Object key : keys) {
      String workType = ((String) key).substring("Calculate".length() + 1).toUpperCase(Locale.getDefault());
      System.out.println(workType);
      String values = properties.getProperty((String) key);
      System.out.println(values);
      addWorkToList(workList, workType, values);
    }
    return workList;
  }

  private void addWorkToList(Collection<Work> workList, String workType, String values) {
    workList.add(new Work(Calculator.valueOf(workType), Arrays.asList(values.split(","))));
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "WorkLoader(properties=" + this.getProperties() + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof WorkLoader)) return false;
    WorkLoader other = (WorkLoader) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$properties = this.getProperties();
    Object other$properties = other.getProperties();
    if (this$properties == null ? other$properties != null : !this$properties.equals(other$properties)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof WorkLoader;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $properties = this.getProperties();
    result = result * PRIME + ($properties == null ? 43 : $properties.hashCode());
    return result;
  }
}
