package asm;

import static org.objectweb.asm.Opcodes.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public final class ClassCreationDemoMaker {

  private static final String VERSION = "version";
  private static final String STRINGBUILDER = "java/lang/StringBuilder";
  private static final String CLASS = "asm/ClassCreationDemo";

  private ClassCreationDemoMaker() {
    throw new AssertionError("Private constructor");
  }

  @SuppressWarnings("checkstyle:ExecutableStatementCount")
  public static byte[] dump() throws IOException {

    // ClassWriter is a class visitor that generates the code for the class
    ClassWriter cw = new ClassWriter(0);
    // Start creating the class.
    cw.visit(
        V11, ACC_PUBLIC + ACC_SUPER, CLASS, null, "java/lang/Object", null);
    FieldVisitor fv;
    MethodVisitor mv;
    {
      // version field
      fv = cw.visitField(ACC_PRIVATE, VERSION, "I", null, null);
      fv.visitEnd();
    }
    {
      // Implementing the constructor
      mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(
          INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
      mv.visitInsn(RETURN);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
    {
      // getVersion Method
      mv = cw.visitMethod(ACC_PUBLIC, "getVersion", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, CLASS, VERSION, "I");
      mv.visitInsn(IRETURN);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
    {
      // setVersion Method
      mv = cw.visitMethod(ACC_PUBLIC, "setVersion", "(I)V", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitVarInsn(ILOAD, 1);
      mv.visitFieldInsn(PUTFIELD, CLASS, VERSION, "I");
      mv.visitInsn(RETURN);
      mv.visitMaxs(2, 2);
      mv.visitEnd();
    }
    {
      // toString method
      mv = cw.visitMethod(
          ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitTypeInsn(NEW, STRINGBUILDER);
      mv.visitInsn(DUP);
      mv.visitLdcInsn("ClassCreationDemo: ");
      mv.visitMethodInsn(INVOKESPECIAL,
                         STRINGBUILDER,
                         "<init>",
                         "(Ljava/lang/String;)V",
                         false);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(GETFIELD, CLASS, VERSION, "I");
      mv.visitMethodInsn(INVOKEVIRTUAL,
                         STRINGBUILDER,
                         "append",
                         "(I)Ljava/lang/StringBuilder;",
                         false);
      mv.visitMethodInsn(INVOKEVIRTUAL,
                         STRINGBUILDER,
                         "toString",
                         "()Ljava/lang/String;",
                         false);
      mv.visitInsn(ARETURN);
      mv.visitMaxs(3, 1);
      mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }

  public static void main(String... args) throws IOException {
    try (DataOutputStream dout = new DataOutputStream(
             Files.newOutputStream(Paths.get("ClassCreationDemo.class")))) {
      dout.write(dump());
      dout.flush();
    }
  }
}
