package asm.instrumentation;

import asm.CustomClassWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Premain.
 *
 * @author baeldung
 */
public final class Premain {

  private Premain() {
    throw new AssertionError("Private constructor");
  }

  public static void agentmain(String agentArgs, Instrumentation inst) {
    premain(agentArgs, inst);
  }

  @SuppressWarnings("checkstyle:DescendantToken")
  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println("Start agent");
    inst.addTransformer(new ClassFileTransformer() {
      @Override
      public byte[] transform(
          ClassLoader l, String name, Class<?> c, ProtectionDomain d, byte[] b)
          throws IllegalClassFormatException {
        if ("asm/MyClass".equals(name)) {
          System.out.println("MyClass loaded");
          CustomClassWriter cr = new CustomClassWriter(b);
          cr.addField();
          cr.addInterface();
          return cr.publicizeMethod();
        }
        return b;
      }
    });
  }
}
