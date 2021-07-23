package varhandles;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

@SuppressWarnings({"PMD", "checkstyle"})
public class VarHandles {

  public int publicTestVar = 1;
  private int privateTestVar = 1;
  public int variableToSet = 1;
  public int variableToCompareAndSet = 1;
  public int variableToGetAndAdd = 0;
  public byte variableToBitwiseOr = 0;

  public static void main(String... args) throws Exception {

    whenVarHandleForPublicVarIsCreated_ThenItIsInitializedProperly();
    whenVarHandleForPrivateVarIsCreated_ThenItIsInitializedProperly();
    whenVarHandleForArrayVarIsCreated_ThenItIsInitializedProperly();
    givenVarHandle_whenGetIsInvoked_ThenValueOfVarIsReturned();
    givenVarHandle_whenSetIsInvoked_ThenValueOfVarIsChanged();
    givenVarHandle_whenCompareAndSetIsInvoked_ThenValueOfVarIsChanged();
    givenVarHandle_whenGetAndAddIsInvoked_ThenValueOfVarIsChanged();
    givenVarHandle_whenGetAndBitwiseOrIsInvoked_ThenValueOfVarIsChanged();
  }

  public static void
  whenVarHandleForPublicVarIsCreated_ThenItIsInitializedProperly()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandles vh = new VarHandles();
    VarHandle PUBLIC_TEST_VARIABLE =
        MethodHandles.lookup()
            .in(VarHandles.class)
            .findVarHandle(VarHandles.class, "publicTestVar", int.class);

    assert 1 == PUBLIC_TEST_VARIABLE.coordinateTypes().size();
    assert VarHandles.class.equals(
        PUBLIC_TEST_VARIABLE.coordinateTypes().get(0));
  }

  public static void
  whenVarHandleForPrivateVarIsCreated_ThenItIsInitializedProperly()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandle PRIVATE_TEST_VARIABLE =
        MethodHandles.privateLookupIn(VarHandles.class, MethodHandles.lookup())
            .findVarHandle(VarHandles.class, "privateTestVar", int.class);

    assert 1 == PRIVATE_TEST_VARIABLE.coordinateTypes().size();
    assert VarHandles.class.equals(
        PRIVATE_TEST_VARIABLE.coordinateTypes().get(0));
  }

  public static void
  whenVarHandleForArrayVarIsCreated_ThenItIsInitializedProperly()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandle arrayVarHandle = MethodHandles.arrayElementVarHandle(int[].class);

    assert 2 == arrayVarHandle.coordinateTypes().size();
    assert int[].class.equals(arrayVarHandle.coordinateTypes().get(0));
  }

  public static void givenVarHandle_whenGetIsInvoked_ThenValueOfVarIsReturned()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandles vh = new VarHandles();
    VarHandle PUBLIC_TEST_VARIABLE =
        MethodHandles.lookup()
            .in(VarHandles.class)
            .findVarHandle(VarHandles.class, "publicTestVar", int.class);

    assert 1 == (int)PUBLIC_TEST_VARIABLE.get(vh);
  }

  public static void givenVarHandle_whenSetIsInvoked_ThenValueOfVarIsChanged()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandles vh = new VarHandles();
    VarHandle VARIABLE_TO_SET =
        MethodHandles.lookup()
            .in(VarHandles.class)
            .findVarHandle(VarHandles.class, "variableToSet", int.class);

    VARIABLE_TO_SET.set(vh, 15);
    assert 15 == (int)VARIABLE_TO_SET.get(vh);
  }

  public static void
  givenVarHandle_whenCompareAndSetIsInvoked_ThenValueOfVarIsChanged()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandles vh = new VarHandles();
    VarHandle VARIABLE_TO_COMPARE_AND_SET =
        MethodHandles.lookup()
            .in(VarHandles.class)
            .findVarHandle(
                VarHandles.class, "variableToCompareAndSet", int.class);

    VARIABLE_TO_COMPARE_AND_SET.compareAndSet(vh, 1, 100);
    assert 100 == (int)VARIABLE_TO_COMPARE_AND_SET.get(vh);
  }

  public static void
  givenVarHandle_whenGetAndAddIsInvoked_ThenValueOfVarIsChanged()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandles vh = new VarHandles();
    VarHandle VARIABLE_TO_GET_AND_ADD =
        MethodHandles.lookup()
            .in(VarHandles.class)
            .findVarHandle(VarHandles.class, "variableToGetAndAdd", int.class);

    int before = (int)VARIABLE_TO_GET_AND_ADD.getAndAdd(vh, 200);

    assert 0 == before;
    assert 200 == (int)VARIABLE_TO_GET_AND_ADD.get(vh);
  }

  public static void
  givenVarHandle_whenGetAndBitwiseOrIsInvoked_ThenValueOfVarIsChanged()
      throws NoSuchFieldException, IllegalAccessException {
    VarHandles vh = new VarHandles();
    VarHandle VARIABLE_TO_BITWISE_OR =
        MethodHandles.lookup()
            .in(VarHandles.class)
            .findVarHandle(VarHandles.class, "variableToBitwiseOr", byte.class);
    byte before = (byte)VARIABLE_TO_BITWISE_OR.getAndBitwiseOr(vh, (byte)127);

    assert 0 == before;
    assert 127 == (byte)VARIABLE_TO_BITWISE_OR.get(vh);
  }
}
