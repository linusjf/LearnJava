package com.lambdaworks.jni;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.

import static java.lang.System.getProperty;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

import java.util.regex.Pattern;

/**
 * A platform is a unique combination of CPU architecture and operating system.
 * This class attempts to determine the platform it is executing on by examining
 * and normalizing the <code>os.arch
 * </code> and <code>os.name</code> system properties.
 *
 * @author Will Glozer
 */
public final class Platform {
  public enum Arch {
    X86("x86|i386"),
    X86_64("x86_64|amd64"),
    AARCH64("aarch64");

    Pattern pattern;

    Arch(String pattern) {
      this.pattern = Pattern.compile("\\A" + pattern + "\\Z", CASE_INSENSITIVE);
    }
  }

  public enum OS {
    DARWIN("darwin|mac os x"),
    FREEBSD("freebsd"),
    LINUX("linux");

    Pattern pattern;

    OS(String pattern) {
      this.pattern = Pattern.compile("\\A" + pattern + "\\Z", CASE_INSENSITIVE);
    }
  }

  public final Arch arch;
  public final OS os;

  private Platform(Arch arch, OS os) {
    this.arch = arch;
    this.os = os;
  }

  /**
   * Attempt to detect the current platform.
   *
   * @return The current platform.
   * @throws UnsupportedPlatformException if the platform cannot be detected.
   */
  public static Platform detect() {
    final String osArch = getProperty("os.arch");
    final String osName = getProperty("os.name");

    for (Arch arch: Arch.values()) {
      if (arch.pattern.matcher(osArch).matches()) {
        for (OS os: OS.values()) {
          if (os.pattern.matcher(osName).matches()) {
            return new Platform(arch, os);
          }
        }
      }
    }

    final String msg =
        String.format("Unsupported platform %s %s", osArch, osName);
    throw new UnsupportedPlatformException(msg);
  }
}
