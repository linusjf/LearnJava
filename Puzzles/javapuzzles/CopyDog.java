package javapuzzles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("PMD.SystemPrintln")
public enum CopyDog {
  // Not to be confused with copycat
  ;

  @SuppressWarnings("PMD.CompareObjectsWithEquals")
  public static void main(String[] args) {
    Dog newDog = (Dog)deepCopy(Dog.INSTANCE);
    System.out.println(newDog == Dog.INSTANCE);
    System.out.println(newDog);
  }

  // This method is very slow and generally a bad idea!
  public static Object deepCopy(Object obj) {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      new ObjectOutputStream(bos).writeObject(obj);
      ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
      return new ObjectInputStream(bin).readObject();
    } catch (ClassNotFoundException | IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
