package eu.javaspecialists.reflection;

import static eu.javaspecialists.reflection.ReflectionHelper.*;
import static java.util.Objects.requireNonNull;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("PMD.LawOfDemeter")
public class EnumBuster<E extends Enum<E>> {
  private final Class<E> clazz;
  private final Collection<Field> switchFields;
  private final Deque<Memento> undoStack = new ArrayDeque<>();

  /**
   * Constructor.
   *
   * @param clazz class
   * @param switchUsers array of class objects Construct an EnumBuster for the given enum class and
   *     keep the switch statements of the classes specified in switchUsers in sync with the enum
   *     values.
   */
  public EnumBuster(Class<E> clazz, Class<?>... switchUsers) {
    this.clazz = clazz;
    switchFields = findRelatedSwitchFields(switchUsers);
  }

  /**
   * Make enum.
   *
   * @param value enum name
   * @return enum value
   * @throws ReflectiveOperationException exception Make a new enum instance, without adding it to
   *     the values array and using the default ordinal of 0.
   */
  public E make(String value) throws ReflectiveOperationException {
    return makeEnum(clazz, value);
  }

  /**
   * This method adds the given enum into the array inside the enum class. If the enum already
   * contains that particular value, then the value is overwritten with our enum. Otherwise it is
   * added at the end of the array.
   *
   * <p>In addition, if there is a constant field in the enum class pointing to an enum with our
   * value, then we replace that with our enum instance.
   *
   * <p>The ordinal is either set to the existing position or to the last value.
   *
   * <p>Warning: This should probably never be called, since it can cause permanent changes to the
   * enum values. Use only in extreme conditions.
   *
   * @param e the enum to add
   * @throws ReflectiveOperationException exception
   */
  public void addByValue(E e) throws ReflectiveOperationException {
    undoStack.push(new Memento());

    // we get the current Enum[]
    E[] values = values();
    for (int i = 0; i < values.length; i++) {
      E value = values[i];
      if (value.name().equals(e.name())) {
        setOrdinal(e, value.ordinal());
        values[i] = e;
        replaceConstant(e);
        return;
      }
    }

    // we did not find it in the existing array, thus
    // append it to the array
    Field valuesField = findValuesField();
    E[] newValues = Arrays.copyOf(values, values.length + 1);
    newValues[newValues.length - 1] = e;
    setStaticFinalField(valuesField, newValues);

    int ordinal = newValues.length - 1;
    setOrdinal(e, ordinal);
    addSwitchCase();
  }

  /**
   * We delete the enum from the values array and set the constant pointer to null.
   *
   * @param e the enum to delete from the type.
   * @return true if the enum was found and deleted; false otherwise
   * @throws ReflectiveOperationException exception
   */
  public boolean deleteByValue(E e) throws ReflectiveOperationException {
    requireNonNull(e);
    undoStack.push(new Memento());
    // we get the current E[]
    E[] values = values();
    for (int i = 0; i < values.length; i++) {
      E value = values[i];
      if (value.name().equals(e.name())) {
        E[] newValues = Arrays.copyOf(values, values.length - 1);
        System.arraycopy(values, i + 1, newValues, i, values.length - i - 1);
        for (int j = i; j < newValues.length; j++) {
          setOrdinal(newValues[j], j);
        }
        Field valuesField = findValuesField();
        setStaticFinalField(valuesField, newValues);
        removeSwitchCase(i);
        blankOutConstant(e);
        return true;
      }
    }
    return false;
  }

  /**
   * Undo the state right back to the beginning when the EnumBuster was created.
   *
   * @throws ReflectiveOperationException exception
   */
  public void restore() throws ReflectiveOperationException {
    while (undo()) {
      // empty loop
    }
  }

  /**
   * Undo the previous operation.
   * @return true or false
   * @throws ReflectiveOperationException exception
   */
  public boolean undo() throws ReflectiveOperationException {
    Memento memento = undoStack.poll();
    if (memento == null)
      return false;
    memento.undo();
    return true;
  }

  /**
   * The only time we ever add a new enum is at the end. Thus all we need to do is expand the switch
   * map arrays by one empty slot.
   *
   * @throws ReflectiveOperationException exception
   */
  private void addSwitchCase() throws ReflectiveOperationException {
    for (Field switchField: switchFields) {
      int[] switches = (int[])switchField.get(null);
      switches = Arrays.copyOf(switches, switches.length + 1);
      setStaticFinalField(switchField, switches);
    }
  }

  /**
   * Replace constant.
   *
   * @param e const name
   */
  private void replaceConstant(E e) throws ReflectiveOperationException {
    Field[] fields = clazz.getDeclaredFields();
    for (Field field: fields) {
      if (field.getName().equals(e.name())) {
        setStaticFinalField(field, e);
      }
    }
  }

  /**
   * Blank out constant.
   *
   * @param e const name
   */
  private void blankOutConstant(E e) throws ReflectiveOperationException {
    Field[] fields = clazz.getDeclaredFields();
    for (Field field: fields) {
      if (field.getName().equals(e.name())) {
        setStaticFinalField(field, null);
      }
    }
  }

  private void setOrdinal(E e, int ordinal)
      throws NoSuchFieldException, IllegalAccessException {
    Field ordinalField = Enum.class.getDeclaredField("ordinal");
    ordinalField.setAccessible(true);
    ordinalField.set(e, ordinal);
  }

  /**
   * Method to find the values field, set it to be accessible, and return it.
   *
   * @return the values array field for the enum.
   * @throws NoSuchFieldException if the field could not be found
   */
  private Field findValuesField() throws NoSuchFieldException {
    // first we find the static final array that holds
    // the values in the enum class
    Field valuesField = clazz.getDeclaredField("$VALUES");
    // we mark it to be public
    valuesField.setAccessible(true);
    return valuesField;
  }

  private Collection<Field> findRelatedSwitchFields(Class<?>... switchUsers) {
    Collection<Field> result = new ArrayList<>();
    for (Class<?> switchUser: switchUsers) {
      Class<?>[] clazzes = getAnonymousClasses(switchUser);
      for (Class<?> suspect: clazzes) {
        Field[] fields = suspect.getDeclaredFields();
        for (Field field: fields) {
          if (field.getName().startsWith("$SwitchMap$"
                                         + clazz.getName().replace(".", "$"))) {
            field.setAccessible(true);
            result.add(field);
          }
        }
      }
    }
    return result;
  }

  private void removeSwitchCase(int ordinal)
      throws ReflectiveOperationException {
    for (Field switchField: switchFields) {
      int[] switches = (int[])switchField.get(null);
      int[] newSwitches = Arrays.copyOf(switches, switches.length - 1);
      System.arraycopy(switches,
                       ordinal + 1,
                       newSwitches,
                       ordinal,
                       switches.length - ordinal - 1);
      setStaticFinalField(switchField, newSwitches);
    }
  }

  private E[] values() throws ReflectiveOperationException {
    return (E[])findValuesField().get(null);
  }

  private final class Memento {
    private final E[] values;
    private final Map<Field, int[]> savedSwitchFieldValues = new HashMap<>();

    private Memento() throws ReflectiveOperationException {
      values = values().clone();
      for (Field switchField: switchFields) {
        int[] switchArray = (int[])switchField.get(null);
        savedSwitchFieldValues.put(switchField, switchArray.clone());
      }
    }

    private void undo() throws ReflectiveOperationException {
      Field valuesField = findValuesField();
      setStaticFinalField(valuesField, values);

      for (int i = 0; i < values.length; i++) {
        setOrdinal(values[i], i);
      }

      // reset all of the constants defined inside the enum
      Map<String, E> valuesMap = new HashMap<>();
      for (E e: values) {
        valuesMap.put(e.name(), e);
      }
      Field[] constantEnumFields = clazz.getDeclaredFields();
      for (Field constantEnumField: constantEnumFields) {
        E en = valuesMap.get(constantEnumField.getName());
        if (en != null) {
          setStaticFinalField(constantEnumField, en);
        }
      }

      for (Map.Entry<Field, int[]> entry: savedSwitchFieldValues.entrySet()) {
        Field field = entry.getKey();
        int[] mappings = entry.getValue();
        setStaticFinalField(field, mappings);
      }
    }
  }
}
