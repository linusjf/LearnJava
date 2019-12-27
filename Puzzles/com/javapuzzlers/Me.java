package com.javapuzzlers;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("PMD")
public final class Me {
  private Me() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    System.out.println(Me.class.getName().replaceAll(".", "/") + ".class");
    System.out.println(Me.class.getName().replaceAll("\\.", "/") + ".class");
    System.out.println(Me.class.getName().replaceAll(Pattern.quote("."), "/")
                       + ".class");
    System.out.println(Me.class.getName().replaceAll("\\.", File.separator)
                       + ".class");
    System.out.println(Me.class.getName().replaceAll("\\.", "\\\\") + ".class");
    System.out.println(Me.class.getName().replaceAll(
                           "\\.", Matcher.quoteReplacement(File.separator))
                       + ".class");
    System.out.println(
        Me.class.getName().replaceAll("\\.", Matcher.quoteReplacement("\\"))
        + ".class");
    System.out.println(Me.class.getName().replace(".", File.separator)
                       + ".class");
    System.out.println(Me.class.getName().replace('.', File.separatorChar)
                       + ".class");
    System.out.println(Me.class.getName().replace('.', '\\') + ".class");
  }
}
