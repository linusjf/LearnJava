package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TypeNameRegex {
  ;

  public static String getTypenameComponent(String classname, int group) {
    // regex is: (?:(.*)\.)?([^\.]*)
    Pattern p = Pattern.compile("(?:(.*)\\.)?([^\\.]*)");
    Matcher m = p.matcher(classname);
    return m.matches() ? m.group(group) : null;
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
