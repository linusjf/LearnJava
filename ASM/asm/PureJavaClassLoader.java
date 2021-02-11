package asm;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.ClassReader;

@SuppressWarnings({"PMD.SystemPrintln", "PMD.LawOfDemeter"})
public final class PureJavaClassLoader extends ClassLoader {

  private final List<String> auxClasspath = new ArrayList<>();

  public PureJavaClassLoader(ClassLoader parent) {
    super(parent);
  }

  public void setupClasspath(final String auxiliaryClassPath) {
    for (String entry: auxiliaryClassPath.split(":")) {
      if (entry.startsWith("/"))
        auxClasspath.add(entry);
      else
        System.err.println("Bad classpath entry seen: " + entry + ", ignoring");
    }
  }

  Path findClassFile(String qualifiedClassName) throws IOException {
    final String fileName =
        qualifiedClassName.replaceAll("/", "\\.") + ".class";
    for (String s: auxClasspath) {
      Path trial = Paths.get(s, fileName);
      if (trial.toFile().exists())
        return trial;
    }
    throw new IOException("Class " + qualifiedClassName
                          + " not found on classpath");
  }

  @Override
  public Class<?> findClass(final String qualifiedClassName)
      throws ClassNotFoundException {
    try {
      return super.findClass(qualifiedClassName);
    } catch (ClassNotFoundException ignored) {
      // empty catch block
    }
    try (InputStream in =
             Files.newInputStream(findClassFile(qualifiedClassName))) {
      final byte[] allClassBytes = in.readAllBytes();
      final ClassReader classReader = new ClassReader(allClassBytes);

      // PrintWriter printWriter = new PrintWriter(System.out);
      // TraceClassVisitor traceClassVisitor = new TraceClassVisitor(printWriter);
      final PureJavaCheckingClassVisitor classVisitor =
          new PureJavaCheckingClassVisitor();

      // If there's debug info in the class, don't look at that whilst visiting
      classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);

      // Did we see a native method
      if (classVisitor.hasNative())
        throw new ClassNotFoundException(
            "Class cannot be loaded - contains native code");
      else
        return defineClass(null, allClassBytes, 0, allClassBytes.length);
    } catch (IOException e) {
      throw new ClassNotFoundException("Error finding and opening class ", e);
    }
  }
}
