package asm;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

@SuppressWarnings({"PMD.SystemPrintln", "PMD.LawOfDemeter"})
public final class OfflineUnsynchronizer {

  private static final Pattern CLASSFILE_NAME = Pattern.compile("(.*)\\.class");

  public static void main(String[] args) {
    OfflineUnsynchronizer offline = new OfflineUnsynchronizer();
    String fName = null;
    if (args.length > 0) {
      fName = args[0];
    } else {
      fName = "asm/Counter.class";
    }
    offline.run(fName);
  }

  private void run(String fName) {
    try (InputStream in = Files.newInputStream(Paths.get(fName))) {
      ClassReader classReader = new ClassReader(in);
      ClassWriter writer =
          new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);

      ClassVisitor unsynchronizer = new UnsynchronizingClassVisitor(writer);
      classReader.accept(unsynchronizer,
                         ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);

      Path newClazz = Paths.get(fName);
      Files.write(newClazz, writer.toByteArray());
    } catch (IOException ex) {
      System.err.println("Exception whilst reading class: " + fName);
      ex.printStackTrace(System.err);
    }
  }

  public static String transformName(String fName) {
    Matcher m = CLASSFILE_NAME.matcher(fName);
    if (m.find()) {
      return "asm/" + m.group(1) + ".class";
    }
    throw new IllegalStateException(fName + " is not a legal filename");
  }
}
