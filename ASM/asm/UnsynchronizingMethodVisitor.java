package asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UnsynchronizingMethodVisitor extends MethodVisitor {

  public UnsynchronizingMethodVisitor(MethodVisitor mv) {
    super(Opcodes.ASM5, mv);
  }

  @SuppressWarnings({"PMD", "checkstyle:MissingSwitchDefault"})
  @Override
  public void visitInsn(final int opcode) {
    switch (opcode) {
      case Opcodes.MONITORENTER:
      case Opcodes.MONITOREXIT:
        super.visitInsn(Opcodes.POP);
        return;
    }
    super.visitInsn(opcode);
  }
}
