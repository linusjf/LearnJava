package reflection;

import static java.lang.System.out;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public enum ClassDeclarationSpy {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(ClassDeclarationSpy.class.getName());

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    try {
      Class<?> c = Class.forName(args[0]);
      printClassDeclaration(c);
      // production code should handle this exception more gracefully
    } catch (ClassNotFoundException x) {
      LOGGER.severe(x.getMessage());
    }
  }

  private static void printClassDeclaration(Class<?> c) {
    out.format("Class:%n  %s%n%n", c.getCanonicalName());
    out.format("Modifiers:%n  %s%n%n", Modifier.toString(c.getModifiers()));

    out.format("Type Parameters:%n");
    TypeVariable<?>[] tv = c.getTypeParameters();
    if (tv.length > 0) {
      out.format("  ");
      for (TypeVariable<?> t: tv)
        out.format("%s ", t.getName());
      out.format("%n%n");
    } else
      out.format("  -- No Type Parameters --%n%n");

    out.format("Implemented Interfaces:%n");
    Type[] intfs = c.getGenericInterfaces();
    if (intfs.length > 0) {
      for (Type intf: intfs)
        out.format("  %s%n", intf.toString());
      out.format("%n");
    } else
      out.format("  -- No Implemented Interfaces --%n%n");

    out.format("Inheritance Path:%n");
    List<Class<?>> l = new ArrayList<Class<?>>();
    printAncestor(c, l);
    if (l.isEmpty())
      out.format("  -- No Super Classes --%n%n");
    else {
      for (Class<?> cl: l)
        out.format("  %s%n", cl.getCanonicalName());
      out.format("%n");
    }

    out.format("Annotations:%n");
    Annotation[] ann = c.getAnnotations();
    if (ann.length > 0) {
      for (Annotation a: ann)
        out.format("  %s%n", a.toString());
      out.format("%n");
    } else
      out.format("  -- No Annotations --%n%n");
  }

  private static void printAncestor(Class<?> c, List<Class<?>> l) {
    Class<?> ancestor = c.getSuperclass();
    if (ancestor == null)
      return;
    else {
      l.add(ancestor);
      printAncestor(ancestor, l);
    }
  }
}
