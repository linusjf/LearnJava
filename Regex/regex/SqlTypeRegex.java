package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SqlTypeRegex {
  ;

  public static void main(String... args) {

    String sqlType = "NUMBER(10,2)";
    String type = getColumnDatatypeComponent(sqlType, 1);
    String length = getColumnDatatypeComponent(sqlType, 2);
    String precision = getColumnDatatypeComponent(sqlType, 3);
    System.out.println("sql type: " + type);
    System.out.println("length: " + length);
    System.out.println("precision: " + precision);
  }

  public static String getColumnDatatypeComponent(String dataType, int group) {
    // ([^\(]+)(?:\((\d+)(?:,(\d+))?\))?
    final String regex = "([^\\(]+)(?:\\((\\d+)(?:,(\\d+))?\\))?";
    return getCapturedGroup(dataType.replaceAll("\\s*", ""), regex, group);
  }

  public static String getCapturedGroup(String value, String pattern, int group) {
    Matcher m = Pattern.compile(pattern).matcher(value);
    if (m.matches() && group >= 0 && group <= m.groupCount()) {
      return m.group(group);
    } else return null;
  }
}
