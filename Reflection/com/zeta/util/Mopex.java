package com.zeta.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mopex {
  /**
   * Returns an array of the instance variablies of the
   * the specified class.  An instance variable is defined
   * to be a non-static field that is declared by the class
   * or inherited.
   */
  public static Field[] getInstanceVariables(Class<?> cls) {
    List<Field> accum = new LinkedList<>();
    while (cls != null) {
      Field[] fields = cls.getDeclaredFields();
      for (Field field: fields) {
        if (!Modifier.isStatic(field.getModifiers())) {
          accum.add(field);
        }
      }
      cls = cls.getSuperclass();
    }
    Field[] retvalue = new Field[accum.size()];
    return accum.toArray(retvalue);
  }

  /**
   * Returns an array of the methods that are not static.
   */
  public static Method[] getInstanceMethods(Class<?> cls) {
    List<Method> instanceMethods = new ArrayList<>();
    for (Class<?> c = cls; c != null; c = c.getSuperclass()) {
      Method[] methods = c.getDeclaredMethods();
      for (Method method: methods)
        if (!Modifier.isStatic(method.getModifiers()))
          instanceMethods.add(method);
    }
    Method[] ims = new Method[instanceMethods.size()];
    for (int j = 0; j < instanceMethods.size(); j++)
      ims[j] = instanceMethods.get(j);
    return ims;
  }

  /**
   * Finds the first (from the bottom of the inheritance hierarchy) field
   * with the specified name. Note that Class.getField returns only
   * public fields.
   */
  public static Field findField(Class<?> cls, String name)
      throws NoSuchFieldException {
    if (cls != null) {
      try {
        return cls.getDeclaredField(name);
      } catch (NoSuchFieldException e) {
        return findField(cls.getSuperclass(), name);
      }
    } else {
      throw new NoSuchFieldException();
    }
  }

  /**
   * Creates constructor with the signature of c and a new name.  It adds some
   * code after generating a super statement to call c.  This method is used
   * when generating a subclass of class that declared c.
   */
  public static String createRenamedConstructor(Constructor<?> c,
                                                String name,
                                                String code) {
    Class<?>[] pta = c.getParameterTypes();
    String fpl = formalParametersToString(pta);
    String apl = actualParametersToString(pta);
    Class<?>[] eTypes = c.getExceptionTypes();
    String result = name + "(" + fpl + ")\n";
    if (eTypes.length != 0)
      result += "    throws " + classArrayToString(eTypes) + "\n";
    result += "{\n    super(" + apl + ");\n" + code + "}\n";
    return result;
  }

  /**
   * Returns a string that can be used as a formal parameter list
   * for a method that has the parameter types of the specified array.
   */
  private static String formalParametersToString(Class<?>[] pts) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < pts.length; i++) {
      result.append(getTypeName(pts[i])).append(" p").append(i);
      if (i < pts.length - 1)
        result.append(",");
    }
    return result.toString();
  }

  /**
   * Returns a string that is an actual parameter list that matches
   * the formal parameter list produced by formalParametersToString.
   */
  private static String actualParametersToString(Class<?>[] pts) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < pts.length; i++) {
      result.append("p").append(i);
      if (i < pts.length - 1)
        result.append(",");
    }
    return result.toString();
  }

  /**
   * Returns a String that is a comma separated list of the
   * typenames of the classes in the array pts.
   */
  private static String classArrayToString(Class<?>[] pts) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < pts.length; i++) {
      result.append(getTypeName(pts[i]));
      if (i < pts.length - 1)
        result.append(",");
    }
    return result.toString();
  }

  /**
   * Returns a syntactically correct name for a class object.
   * If the class object represents an array, the proper
   * number of square bracket pairs are appended to the component type.
   */
  private static String getTypeName(Class<?> cls) {
    if (!cls.isArray()) {
      return cls.getName();
    } else {
      return getTypeName(cls.getComponentType()) + "[]";
    }
  }
}
