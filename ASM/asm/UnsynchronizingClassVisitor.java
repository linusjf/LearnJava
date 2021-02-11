package asm;

import static org.objectweb.asm.Opcodes.ACC_SYNCHRONIZED;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UnsynchronizingClassVisitor extends ClassVisitor {

  public UnsynchronizingClassVisitor(ClassVisitor cv) {
    super(Opcodes.ASM5, cv);
  }

  @Override
  public MethodVisitor visitMethod(int flags,
                                   String name,
                                   String desc,
                                   String signature,
                                   String[] exceptions) {
    int maskedFlags = flags & (~ACC_SYNCHRONIZED);

    MethodVisitor baseMethodVisitor =
        super.visitMethod(maskedFlags, name, desc, signature, exceptions);

    return new UnsynchronizingMethodVisitor(baseMethodVisitor);
  }
}
