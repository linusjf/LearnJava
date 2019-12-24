package reflection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("PMD.UseProperClassLoader")
public enum Loaded {
  ;
  private static final String REFLECTABLE_CLASS = "reflection.ReflectableClass";

  private static ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
  private static ClassLoader classClassLoader = ReflectableClass.class.getClassLoader();

  @SuppressWarnings({"PMD.CompareObjectsWithEquals", "PMD.LawOfDemeter"})
  public static void main(String... args) {
    try {
      Class<?> reflectableClassInstanceLoaded = systemClassLoader.loadClass(REFLECTABLE_CLASS);
      Class<?> reflectableClassInstanceForName =
          Class.forName(REFLECTABLE_CLASS, true, systemClassLoader);
      Class<?> reflectableClassInstanceLoadedClass = classClassLoader.loadClass(REFLECTABLE_CLASS);
      ClassLoader testClassLoader = new TestClassLoader();
      Class<?> classInstance = testClassLoader.loadClass(REFLECTABLE_CLASS);
      System.out.println(reflectableClassInstanceLoaded == reflectableClassInstanceForName);
      System.out.println(reflectableClassInstanceLoaded.equals(reflectableClassInstanceForName));
      System.out.println(reflectableClassInstanceLoadedClass == reflectableClassInstanceForName);
      System.out.println(
          reflectableClassInstanceLoadedClass.equals(reflectableClassInstanceForName));
      System.out.println(classInstance == reflectableClassInstanceForName);
      System.out.println(classInstance.equals(reflectableClassInstanceForName));
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    }
  }

  static class TestClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      if (!REFLECTABLE_CLASS.equals(name)) return super.loadClass(name);
      try (InputStream in =
              ClassLoader.getSystemResourceAsStream(name.replace(".", "/") + ".class");
          ByteArrayOutputStream buffer = new ByteArrayOutputStream(); ) {
        int data = in.read();
        while (data != -1) {
          buffer.write(data);
          data = in.read();
        }

        byte[] a = buffer.toByteArray();
        return defineClass(name, a, 0, a.length);
      } catch (IOException e) {
        throw new ClassNotFoundException(e.getMessage(), e);
      }
    }
  }
}
