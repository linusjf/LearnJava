package design.composite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class WorkLoader {
  protected Properties properties = new Properties();

  public WorkLoader(String fileName) {
    try (InputStream input = new FileInputStream(fileName)) {
      // load a properties file
      properties.load(input);
    } catch (IOException exp) {
      exp.printStackTrace();
    }
    //	properties = PropertyLoader.loadProperties(fileName);
  }

  public Properties getProperties() {
    return properties;
  }

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
