package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.logging.Logger;

public final class Dynamic {

  private Dynamic() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("unchecked")
  public static void main(String... args) {

    Map<Object, Object> proxyInstance =
        (Map<Object, Object>)Proxy.newProxyInstance(
            Thread.currentThread().getContextClassLoader(),
            new Class<?>[] {Map.class},
            new DynamicInvocationHandler());
    proxyInstance.put("hello", "world");
    System.out.println(proxyInstance.get("hello"));
  }

  static class DynamicInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER =
        Logger.getLogger(DynamicInvocationHandler.class.getName());

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      // clang-format off
      LOGGER.info(
          () -> {
            return "Invoked method: " + method.getName();
          });
      // clang-format on
      return 42;
    }
  }
}
