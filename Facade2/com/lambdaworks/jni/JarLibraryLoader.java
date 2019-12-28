package com.lambdaworks.jni;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.
import static com.lambdaworks.jni.Platform.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * A native library loader that will extract and load a shared library contained in a jar. This
 * loader will attempt to detect the {@link Platform platform} (CPU architecture and OS) it is
 * running on and load the appropriate shared library.
 *
 * <p>Given a library path and name this loader looks for a native library with path
 * [libraryPath]/[arch]/[os]/lib[name].[ext]
 *
 * @author Will Glozer
 */
public class JarLibraryLoader implements LibraryLoader {
  File lib;
  private final CodeSource codeSource;
  private final String libraryPath;

  /**
   * Initialize a new instance that looks for shared libraries located in the same jar as this class
   * and with a path starting with {@code lib}.
   */
  public JarLibraryLoader() {
    this(JarLibraryLoader.class.getProtectionDomain().getCodeSource(), "lib");
  }

  /**
   * Initialize a new instance that looks for shared libraries located in the specified directory of
   * the supplied code source.
   *
   * @param codeSource Code source containing shared libraries.
   * @param libraryPath Path prefix of shared libraries.
   */
  public JarLibraryLoader(CodeSource codeSource, String libraryPath) {
    this.codeSource = codeSource;
    this.libraryPath = libraryPath;
  }

  /**
   * Load a shared library, and optionally verify the jar signatures.
   *
   * @param name Name of the library to load.
   * @param verify Verify the jar file if signed.
   * @return true if the library was successfully loaded.
   */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public boolean load(String name, boolean verify) {
    try (JarFile jar =
             new JarFile(codeSource.getLocation().getPath(), verify)) {
      Platform platform = detect();
      for (String path: libCandidates(platform, name)) {
        JarEntry entry = jar.getJarEntry(path);
        if (entry == null)
          continue;
        else {
          lib = extract(name, jar.getInputStream(entry));
          SecurityManager sm = System.getSecurityManager();
          checkLink(sm);
          System.load(lib.getAbsolutePath());
          return lib.delete();
        }
      }
    } catch (SecurityException | IOException e) {
      System.err.println("Unable to load library : " + lib);
    }
    return false;
  }

  private void checkLink(SecurityManager sm) {
    sm.checkLink(lib.getAbsolutePath());
  }

  /**
   * Extract a jar entry to a temp file.
   *
   * @param name Name prefix for temp file.
   * @param is Jar entry input stream.
   * @return A temporary file.
   * @throws IOException when an IO error occurs.
   */
  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  private static File extract(String name, InputStream is) throws IOException {
    final byte[] buf = new byte[4096];

    final File lib = File.createTempFile(name, "lib");
    lib.deleteOnExit();
    try (OutputStream os =
             Files.newOutputStream(Paths.get(lib.getAbsolutePath()))) {
      int len;
      while ((len = is.read(buf)) > 0)
        os.write(buf, 0, len);
    }
    return lib;
  }

  /**
   * Generate a list of candidate libraries for the supplied library name and suitable for the
   * current platform.
   *
   * @param platform Current platform.
   * @param name Library name.
   * @return List of potential library names.
   */
  private List<String> libCandidates(Platform platform, String name) {
    final List<String> candidates = new ArrayList<>();
    final StringBuilder sb = new StringBuilder();

    sb.append(libraryPath)
        .append('/')
        .append(platform.arch)
        .append('/')
        .append(platform.os)
        .append("/lib")
        .append(name);

    switch (platform.os) {
      case DARWIN:
        candidates.add(sb + ".dylib");
        candidates.add(sb + ".jnilib");
        break;
      case LINUX:  // falls through
      case FREEBSD:
        candidates.add(sb + ".so");
        break;
      default:
    }
    return candidates;
  }
}
