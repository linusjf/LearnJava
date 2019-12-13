package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Class to show how to use reflection and generics.
 *
 * @author dgutierrez-diez
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class GenericsClass {
  List<String> internalList;
  Map<String, Object> internalMap;

  public List<String> getInternalList() {
    return internalList;
  }

  public Map<String, Object> getInternalMap() {
    return internalMap;
  }

  public void setInternalList(List<String> internalList) {
    this.internalList = internalList;
  }

  public static void main(String... args) {
    try {
      Method getInternalListMethod =
          GenericsClass.class.getMethod("getInternalList");

      printParameterTypes(getInternalListMethod);

      Method getInternalMapMethod =
          GenericsClass.class.getMethod("getInternalMap");
      printParameterTypes(getInternalMapMethod);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }

  private static void printParameterTypes(Method method) {

      // we get the return type
      Type genericReturnType =
          method.getGenericReturnType();

      // we can check if the return type is parameterized (using
      // ParameterizedType)
      if (genericReturnType instanceof ParameterizedType) {
        ParameterizedType parameterizedType =
            (ParameterizedType)genericReturnType;
printParameterTypes(parameterizedType);
  }
  }

  private static void printParameterTypes(ParameterizedType parameterizedType) {

        // we get the type of the arguments for the parameterized type
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        for (Type typeArgument: typeArguments) {
          Class<? extends Object> typeClass =
              (Class<? extends Object>)typeArgument;
          System.out.println("typeArgument = " + typeArgument);
          System.out.println("typeClass = " + typeClass);
        }
  }
}
