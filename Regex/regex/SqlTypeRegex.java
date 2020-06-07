package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Optional;

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
    Regex regex = new Regex(pattern, value);
    return regex.group(group).get();
  }

  static final class Regex {
    Pattern pattern;
    Matcher matcher;

    Regex(String pattern, String value) {
      this.pattern = Pattern.compile(pattern);
      this.matcher = createMatcher(value);
    }

    private Matcher createMatcher(String value) {
      return pattern.matcher(value);
    }

    public Optional<String> group(int index) {
      if (index >= 0 && index <= matcher.groupCount() && matcher.matches())
        return Optional.ofNullable(matcher.group(index));
      return Optional.ofNullable(null); 
    }
  }
}
