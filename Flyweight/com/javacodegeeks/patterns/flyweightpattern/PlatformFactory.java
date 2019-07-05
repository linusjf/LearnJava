package com.javacodegeeks.patterns.flyweightpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Describe class <code>PlatformFactory</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class PlatformFactory { // NOPMD
  private static Map<String, Platform> map = new HashMap<>();

  private PlatformFactory() {
    throw new AssertionError("Cannot instantiate the class");
  }

  /**
   * Describe <code>getPlatformInstance</code> method here.
   *
   * @param platformType a <code>String</code> value
   * @return a <code>Platform</code> value
   */
  public static Platform getPlatformInstance(String platformType) {
    Platform platform;
    synchronized (map) {
      platform = map.get(platformType);
      if (platform == null) {
        switch (platformType) {
          case "C":
            platform = new CPlatform();
            break;
          case "CPP":
            platform = new CPPPlatform();
            break;
          case "JAVA":
            platform = new JavaPlatform();
            break;
          case "RUBY":
            platform = new RubyPlatform();
            break;
          default:
            break;
        }
        map.put(platformType, platform);
      }
    }
    return platform;
  }
}
