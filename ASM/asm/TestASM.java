package asm;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

@SuppressWarnings("PMD.LawOfDemeter")
public final class TestASM {

  private TestASM() {
    throw new AssertionError("Private constructor");
  }

  public static void main(String... args) {
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    cw.visit(Opcodes.V11,
             Opcodes.ACC_PUBLIC,
             "GeneratedClass",
             null,
             "java/lang/Object",
             null);

    MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                                      "main",
                                      "([Ljava/lang/String;)V",
                                      null,
                                      null);
    mv.visitCode();
    mv.visitFieldInsn(
        Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    mv.visitLdcInsn("Hello world!");
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                       "java/io/PrintStream",
                       "println",
                       "(Ljava/lang/String;)V",
                       false);
    mv.visitInsn(Opcodes.RETURN);
    mv.visitMaxs(0, 0);
    mv.visitEnd();

    cw.visitEnd();

    // Write the bytes as a class file
    byte[] bytes = cw.toByteArray();
    try (OutputStream stream =
             Files.newOutputStream(Paths.get("GeneratedClass.class"))) {
      stream.write(bytes);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
