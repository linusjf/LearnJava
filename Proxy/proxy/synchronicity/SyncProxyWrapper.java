package proxy.synchronicity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SyncProxyWrapper<T> implements InvocationHandler {

  private final T wrappableImpl;
  private final Object lockObject = new Object();

  public SyncProxyWrapper(T wrappableImpl) {
    this.wrappableImpl = wrappableImpl;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    synchronized (lockObject) {
      System.out.println("Synchronized call...");
      return method.invoke(wrappableImpl, args);
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T wrap(Class<T> wrappableClass, T wrappableImpl) {
    return (T)
        Proxy.newProxyInstance(
            SyncProxyWrapper.class.getClassLoader(),
            new Class[] {wrappableClass},
            new SyncProxyWrapper<>(wrappableImpl));
  }
}
