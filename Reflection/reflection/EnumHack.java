package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import sun.misc.Unsafe; // NOPMD

@SuppressWarnings("all")
public final class EnumHack {

  private EnumHack() {
    throw new AssertionError("Private constructor invoked.");
  }

  public static void main(String[] args)  {
try {
    System.out.println(Arrays.toString(Monster.class.getEnumConstants()));
    unsafeWay();
    System.out.println(Arrays.toString(Monster.class.getEnumConstants()));
} catch (ReflectiveOperationException roe) {
  System.err.println(roe.getMessage());
}
  }

  public static void unsafeWay() throws ReflectiveOperationException  {
    Constructor<?> constructor = Unsafe.class.getDeclaredConstructors()[0];
    constructor.setAccessible(true);
    Unsafe unsafe = (Unsafe)constructor.newInstance();
    Monster enumValue = (Monster)unsafe.allocateInstance(Monster.class);

    Field ordinalField = Enum.class.getDeclaredField("ordinal");
    makeAccessible(ordinalField);
    ordinalField.setInt(enumValue, 5);

    Field nameField = Enum.class.getDeclaredField("name");
    makeAccessible(nameField);
    nameField.set(enumValue, "LION");

    Field entityClassField = Monster.class.getDeclaredField("entityClass");
    makeAccessible(entityClassField);
    entityClassField.set(enumValue, Lion.class);

    Field entityIdField = Monster.class.getDeclaredField("entityId");
    makeAccessible(entityIdField);
    entityIdField.set(enumValue, "Lion");

    registerValue(enumValue);
  }

  private static void registerValue(Monster enumValue) throws ReflectiveOperationException {
    // and now we need to replace values reference from final field.
    Field $VALUESField = Monster.class.getDeclaredField("$VALUES");
    makeAccessible($VALUESField);
    // just copy old values to new array and add our new field.
    Monster[] oldValues = (Monster[])$VALUESField.get(null);
    Monster[] newValues = new Monster[oldValues.length + 1];
    System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
    newValues[oldValues.length] = enumValue;
    $VALUESField.set(null, newValues);

    Field enumConstantsField = Class.class.getDeclaredField("enumConstants");
    makeAccessible(enumConstantsField);
    enumConstantsField.set(Monster.class, null);

    Field enumConstantDirectoryField =
        Class.class.getDeclaredField("enumConstantDirectory");
    makeAccessible(enumConstantDirectoryField);
    enumConstantDirectoryField.set(Monster.class, null);
  }

  static void makeAccessible(Field field) throws ReflectiveOperationException {
    field.setAccessible(true);
    // note that every field is just copy of real field, so changed modifiers affects only this
    // Field instance, if you will get this same field again, it
    // will be final again.
    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
  }

  public enum Monster {
    ZOMBIE(Zombie.class, "zombie"),
    ORK(Ork.class, "ork"),
    WOLF(Wolf.class, "wolf");
    private final Class<? extends Entity> entityClass;
    private final String entityId;

    Monster(Class<? extends Entity> entityClass, String entityId) {
      this.entityClass = entityClass;
      this.entityId = "monster:" + entityId;
    }

    public Class<? extends Entity> getEntityClass() {
      return this.entityClass;
    }

    public String getEntityId() {
      return this.entityId;
    }

    public Entity create() {
      try {
        return entityClass.getDeclaredConstructor().newInstance();
      } catch (ReflectiveOperationException e) {
        throw new InternalError(e);
      }
    }
  }
}

interface Entity {
  // empty class body
}

class Zombie implements Entity {
  // empty class body
}

class Ork implements Entity {
  // empty class body
}

class Wolf implements Entity {
  // empty class body
}

class CaerbannogRabbit implements Entity {
  // empty class body
}

class Lion implements Entity {
  // empty class body
}
