package asm;

import static org.objectweb.asm.Opcodes.*;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public final class ClassCreationDemoMaker {

  private ClassCreationDemoMaker() {
    throw new AssertionError("Private constructor");
  }

  @SuppressWarnings("checkstyle:ExecutableStatementCount")
  public static byte[] dump() throws Exception {

    // ClassWriter is a class visitor that generates the code for the class
    ClassWriter cw = new ClassWriter(0);
    FieldVisitor fv;
    MethodVisitor mv;
    // Start creating the class.
    cw.visit(V1_6,
             ACC_PUBLIC + ACC_SUPER,
             "com/geekyarticles/asm/ClassCreationDemo",
             null,
             "java/lang/Object",
             null);

    {
      // version field
      fv = cw.visitField(ACC_PRIVATE, "version", "I", null, null);
      fv.visitEnd();
    }
    {
      // Implementing the constructor
      mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
      mv.visitInsn(RETURN);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
    {
      // getVersion Method
      mv = cw.visitMethod(ACC_PUBLIC, "getVersion", "()I", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(
          GETFIELD, "com/geekyarticles/asm/ClassCreationDemo", "version", "I");
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
      mv.visitFieldInsn(
          PUTFIELD, "com/geekyarticles/asm/ClassCreationDemo", "version", "I");
      mv.visitInsn(RETURN);
      mv.visitMaxs(2, 2);
      mv.visitEnd();
    }
    {
      // toString method
      mv = cw.visitMethod(
          ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
      mv.visitInsn(DUP);
      mv.visitLdcInsn("ClassCreationDemo: ");
      mv.visitMethodInsn(INVOKESPECIAL,
                         "java/lang/StringBuilder",
                         "<init>",
                         "(Ljava/lang/String;)V",
                         false);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitFieldInsn(
          GETFIELD, "asm/ClassCreationDemo", "version", "I");
      mv.visitMethodInsn(INVOKEVIRTUAL,
                         "java/lang/StringBuilder",
                         "append",
                         "(I)Ljava/lang/StringBuilder;",
                         false);
      mv.visitMethodInsn(INVOKEVIRTUAL,
                         "java/lang/StringBuilder",
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

  public static void main(String... args) throws Exception {
    
    DataOutputStream dout =
        new DataOutputStream(new FileOutputStream("ClassCreationDemo.class"));
    dout.write(dump());
    dout.flush();
    dout.close();
  }
}
