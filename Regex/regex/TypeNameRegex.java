package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Optional;

public enum TypeNameRegex {
  ;

  static final Pattern P = Pattern.compile("(?:(.*)\\.)?([^\\.]*)");

  public static String getTypenameComponent(String classname, int group) {
    // regex is: (?:(.*)\.)?([^\.]*)
    Matcher m = P.matcher(classname);
    return findTypeComponent(m, group).get();
  }

  private static Optional<String> findTypeComponent(Matcher m, int group) {
    return m.matches() ? Optional.ofNullable(m.group(group)) 
      : Optional.ofNullable(null);
  }

  public static void main(String... args) {
    String typeName = "com.ociweb.regex.CapturingExample";
    String packageName = getTypenameComponent(typeName, 1);
    String className = getTypenameComponent(typeName, 2);
    System.out.println("package: " + packageName);
    System.out.println("class: " + className);
    // packageName is "com.ociweb.regex",
    // classname is "CapturingExample"
    // non-capturing: (?:(.*)\.) matches package +
    // "."group 1: (.*) matches package
    // group 2: ([^\.]*) matches class name
  }
}
