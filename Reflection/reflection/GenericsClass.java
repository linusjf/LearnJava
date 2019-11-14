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
      Method getInternalListMethod = GenericsClass.class.getMethod("getInternalList");

      // we get the return type
      Type getInternalListMethodGenericReturnType = getInternalListMethod.getGenericReturnType();

      // we can check if the return type is parameterized (using
      // ParameterizedType)
      if (getInternalListMethodGenericReturnType instanceof ParameterizedType) {
        ParameterizedType parameterizedType =
            (ParameterizedType) getInternalListMethodGenericReturnType;

        // we get the type of the arguments for the parameterized type
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        for (Type typeArgument : typeArguments) {
          Class<? extends Object> typeClass = (Class<? extends Object>) typeArgument;
          System.out.println("typeArgument = " + typeArgument);
          System.out.println("typeClass = " + typeClass);
        }
      }
      Method getInternalMapMethod = GenericsClass.class.getMethod("getInternalMap");

      // we get the return type
      Type getInternalMapMethodGenericReturnType = getInternalMapMethod.getGenericReturnType();

      // we can check if the return type is parameterized (using
      // ParameterizedType)
      if (getInternalMapMethodGenericReturnType instanceof ParameterizedType) {
        ParameterizedType parameterizedType =
            (ParameterizedType) getInternalMapMethodGenericReturnType;

        // we get the type of the arguments for the parameterized type
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        for (Type typeArgument : typeArguments) {
          Class<? extends Object> typeClass = (Class<? extends Object>) typeArgument;
          System.out.println("typeArgument = " + typeArgument);
          System.out.println("typeClass = " + typeClass);
        }
      } else System.out.println("Not parameterized type. ..");
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }
}
