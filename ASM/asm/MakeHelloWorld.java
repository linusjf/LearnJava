package asm;

import static org.objectweb.asm.Opcodes.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

@SuppressWarnings("PMD.LawOfDemeter")
public class MakeHelloWorld {

  private final ClassWriter cw = new ClassWriter(0);

  public static void main(String[] args) throws IOException {
    MakeHelloWorld mhw = new MakeHelloWorld();
    String fName = "HelloWorld.class";
    mhw.run(fName);
  }

  private void run(String fName) throws IOException {
    Path p = Paths.get(fName);
    Files.write(p, serializeToBytes("HelloWorld"));
  }

  public byte[] serializeToBytes(String outputClazzName) {
    cw.visit(V1_8,
             ACC_PUBLIC + ACC_SUPER,
             outputClazzName,
             null,
             "java/lang/Object",
             null);
    addStandardConstructor();
    addMainMethod();
    cw.visitEnd();
    return cw.toByteArray();
  }

  void addStandardConstructor() {
    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
    mv.visitVarInsn(ALOAD, 0);
    mv.visitMethodInsn(
        INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
    mv.visitInsn(RETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
  }

  void addMainMethod() {
    MethodVisitor mv = cw.visitMethod(
        ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
    mv.visitCode();
    mv.visitFieldInsn(
        GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    mv.visitLdcInsn("Hello World!");
    mv.visitMethodInsn(INVOKEVIRTUAL,
                       "java/io/PrintStream",
                       "println",
                       "(Ljava/lang/String;)V",
                       false);
    mv.visitInsn(RETURN);
    mv.visitMaxs(3, 3);
    mv.visitEnd();
  }
}
