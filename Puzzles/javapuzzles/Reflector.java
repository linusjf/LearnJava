package javapuzzles;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public enum Reflector {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      Set<String> s = new HashSet<>();
      s.add("foo");
      Iterator<String> it = s.iterator();
      Method m = it.getClass().getMethod("hasNext");
      System.out.println(m.invoke(it));
    } catch (IllegalAccessException | InvocationTargetException
             | NoSuchMethodException iae) {
      System.err.println(iae);
    }
    altMain(args);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void altMain(String... args) {
    try {
      Set<String> s = new HashSet<>();
      s.add("foo");
      Iterator<String> it = s.iterator();
      Method m = Iterator.class.getMethod("hasNext");
      System.out.println(m.invoke(it));
    } catch (IllegalAccessException | InvocationTargetException
             | NoSuchMethodException iae) {
      System.err.println(iae);
    }
  }
}
