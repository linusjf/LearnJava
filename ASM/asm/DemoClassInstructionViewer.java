package asm;

import java.io.IOException;
import java.io.InputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.InstructionAdapter;

@SuppressWarnings("PMD.SystemPrintln")
public final class DemoClassInstructionViewer {
  private DemoClassInstructionViewer() {
    throw new AssertionError("Private constructor");
  }

  public static void main(String... args) throws IOException {
    InputStream in = DemoClassInstructionViewer.class.getResourceAsStream(
        "/asm/ASMHelloWorld.class");
    ClassReader classReader = new ClassReader(in);
    classReader.accept(new MethodPrinterVisitor(Opcodes.ASM9), 0);
  }

  static class MethodPrinterVisitor extends ClassVisitor {

    MethodPrinterVisitor(int api, ClassVisitor cv) {
      super(api, cv);
    }

    MethodPrinterVisitor(int api) {
      super(api);
    }

    @Override
    public MethodVisitor visitMethod(int ignoredAccess,
                                     String name,
                                     String desc,
                                     String ignoredSignature,
                                     String[] ignoredExceptions) {

      System.out.println("\n" + name + desc);

      MethodVisitor oriMv = new MethodVisitor(Opcodes.ASM9) {};
      // An instructionAdapter is a special MethodVisitor that
      // lets us process instructions easily
      return new InstructionAdapter(oriMv) {
        @Override
        public void visitInsn(int opcode) {
          System.out.println(opcode);
          super.visitInsn(opcode);
        }
      };
    }
  }
}
