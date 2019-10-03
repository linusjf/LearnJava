package reflection;

import java.io.IOException;
import java.io.InputStream;

public enum Loaded {
  ;

  @SuppressWarnings({"PMD.UseProperClassLoader",
                     "PMD.CompareObjectsWithEquals"})
  public static void
  main(String... args) {
    try {
      ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
      ClassLoader classClassLoader = ReflectableClass.class.getClassLoader();
      Class<?> reflectableClassInstanceLoaded =
          systemClassLoader.loadClass("reflection.ReflectableClass");
      Class<?> reflectableClassInstanceForName =
          Class.forName("reflection.ReflectableClass", true, systemClassLoader);
      Class<?> reflectableClassInstanceLoadedClass =
          classClassLoader.loadClass("reflection.ReflectableClass");
      ClassLoader testClassLoader = new TestClassLoader();
      Class<?> classInstance =
          testClassLoader.loadClass("reflection.ReflectableClass");
      System.out.println(reflectableClassInstanceLoaded
                         == reflectableClassInstanceForName);
      System.out.println(reflectableClassInstanceLoaded.equals(
          reflectableClassInstanceForName));
      System.out.println(reflectableClassInstanceLoadedClass
                         == reflectableClassInstanceForName);
      System.out.println(reflectableClassInstanceLoadedClass.equals(
          reflectableClassInstanceForName));
      System.out.println(classInstance == reflectableClassInstanceForName);
      System.out.println(classInstance.equals(reflectableClassInstanceForName));
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    }
  }

  static class TestClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      if (!name.equals("reflection.ReflectableClass")) {
        return super.loadClass(name);
      }
      try {
        InputStream in = ClassLoader.getSystemResourceAsStream(
            name.replace(".", "/") + ".class");
        byte[] a = new byte[10000];
        int len = in.read(a);
        in.close();
        return defineClass(name, a, 0, len);
      } catch (IOException e) {
        throw new ClassNotFoundException();
      }
    }
  }
}
