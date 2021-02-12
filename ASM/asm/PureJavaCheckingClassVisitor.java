package asm;

import static org.objectweb.asm.Opcodes.ACC_NATIVE;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PureJavaCheckingClassVisitor extends ClassVisitor {
  private boolean containsNative;

  public PureJavaCheckingClassVisitor() {
    super(Opcodes.ASM9);
  }

  @Override
  public MethodVisitor visitMethod(int flags,
                                   String ignoredName,
                                   String ignoredDesc,
                                   String ignoredSignature,
                                   String[] ignoredExceptions) {
    if ((flags & ACC_NATIVE) != 0)
      containsNative = true;
    return new EmptyMethodVisitor(Opcodes.ASM9);
  }

  public boolean hasNative() {
    return containsNative;
  }

  private static class EmptyMethodVisitor extends MethodVisitor {
    EmptyMethodVisitor(int opcode) {
      super(opcode);
    }
  }
}
