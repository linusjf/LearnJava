package asm;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ASM9;
import static org.objectweb.asm.Opcodes.V11;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

/**
 * CustomClassWriter.
 *
 * @author baeldung
 */
@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
public class CustomClassWriter {

  static final String CLASSNAME = "asm/MyClass";
  static final String CLONEABLE = "java/lang/Cloneable";
  ClassReader reader;
  ClassWriter writer;
  AddFieldAdapter addFieldAdapter;
  AddInterfaceAdapter addInterfaceAdapter;
  PublicizeMethodAdapter pubMethAdapter;

  public CustomClassWriter() {

    try {
      reader = new ClassReader(CLASSNAME);
      writer = new ClassWriter(reader, 0);

    } catch (IOException ex) {
      Logger.getLogger(CustomClassWriter.class.getName())
          .log(Level.SEVERE, null, ex);
    }
  }

  public CustomClassWriter(byte[] contents) {
    reader = new ClassReader(contents);
    writer = new ClassWriter(reader, 0);
  }

  public static void main(String[] args) {
    CustomClassWriter ccw = new CustomClassWriter();
    ccw.addField();
    ccw.addInterface();
    ccw.publicizeMethod();
  }

  public byte[] addField() {
    addFieldAdapter =
        new AddFieldAdapter("aNewBooleanField", ACC_PUBLIC, writer);
    reader.accept(addFieldAdapter, 0);
    return writer.toByteArray();
  }

  public byte[] publicizeMethod() {
    pubMethAdapter = new PublicizeMethodAdapter(writer);
    reader.accept(pubMethAdapter, 0);
    return writer.toByteArray();
  }

  public byte[] addInterface() {
    addInterfaceAdapter = new AddInterfaceAdapter(writer);
    reader.accept(addInterfaceAdapter, 0);
    return writer.toByteArray();
  }

  static class AddInterfaceAdapter extends ClassVisitor {

    AddInterfaceAdapter(ClassVisitor cv) {
      super(ASM9, cv);
    }

    @Override
    public void visit(int ignoredVersion,
                      int access,
                      String name,
                      String signature,
                      String superName,
                      String[] interfaces) {
      String[] holding = new String[interfaces.length + 1];
      holding[holding.length - 1] = CLONEABLE;
      System.arraycopy(interfaces, 0, holding, 0, interfaces.length);

      cv.visit(V11, access, name, signature, superName, holding);
    }
  }

  static class PublicizeMethodAdapter extends ClassVisitor {

    final Logger logger = Logger.getLogger("PublicizeMethodAdapter");
    TraceClassVisitor tracer;
    PrintWriter pw = new PrintWriter(System.out, true, StandardCharsets.UTF_8);

    PublicizeMethodAdapter(ClassVisitor cv) {
      super(ASM9, cv);
      this.cv = cv;
      tracer = new TraceClassVisitor(cv, pw);
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String desc,
                                     String signature,
                                     String[] exceptions) {
      if ("main".equals(name)) {
        logger.info(() -> "Visiting " + name);
        return tracer.visitMethod(
            ACC_PUBLIC + ACC_STATIC, name, desc, signature, exceptions);
      }
      return tracer.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
      tracer.visitEnd();
      System.out.println(tracer.p.getText());
    }
  }

  static class AddFieldAdapter extends ClassVisitor {

    String fieldName;
    int access;
    boolean isFieldPresent;

    AddFieldAdapter(String fieldName, int access, ClassVisitor cv) {
      super(ASM9, cv);
      this.cv = cv;
      this.access = access;
      this.fieldName = fieldName;
    }

    @SuppressWarnings("checkstyle:HiddenField")
    @Override
    public FieldVisitor visitField(int access,
                                   String name,
                                   String desc,
                                   String signature,
                                   Object value) {
      if (name.equals(fieldName))
        isFieldPresent = true;
      return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
      if (!isFieldPresent) {
        FieldVisitor fv = cv.visitField(
            access, fieldName, Type.BOOLEAN_TYPE.toString(), null, null);
        if (fv != null)
          fv.visitEnd();
      }
      cv.visitEnd();
    }
  }
}
