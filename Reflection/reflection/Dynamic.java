package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.logging.Logger;

public class Dynamic {

  @SuppressWarnings("unchecked")
  public static void main(String... args) {

    Map<? super Object,? super Object> proxyInstance =
        (Map<? super Object,? super Object>)Proxy.newProxyInstance(Dynamic.class.getClassLoader(),
                                    new Class<?>[] {Map.class},
                                    new DynamicInvocationHandler());
    proxyInstance.put("hello", "world");
    System.out.println(proxyInstance.get("hello")); 
  }

  static class DynamicInvocationHandler implements InvocationHandler {

    private static Logger LOGGER =
        Logger.getLogger(DynamicInvocationHandler.class.getName());

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      LOGGER.info(() -> { return "Invoked method: " + method.getName(); });
      return 42;
    }
  }
}
