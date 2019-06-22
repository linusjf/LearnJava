package proxy.synchronicity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
 * SyncProxyWrapper class.
 * @param <T> Type parameter
 * */
public class SyncProxyWrapper<T> implements InvocationHandler {
  private final T wrappableImpl;
  private final Object lockObject = new Object();

  /**
   * Constructor.
   *
   * @param wrappableImpl the wrappable class
   */
  public SyncProxyWrapper(T wrappableImpl) {
    this.wrappableImpl = wrappableImpl;
  }

  /**
   * Invoke the specified method on the wrapped implementation.
   *
   * @return Object holds the result of the method invokation.
   * @param proxy the proxy object
   * @param method the method to be invoked
   * @param args array of arguments
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
      throws Throwable {
    synchronized (lockObject) {
      System.out.println("Synchronized call...");
      return method.invoke(wrappableImpl, args);
    }
  }

  /**
   * Wrap the implementor with your synchronous wrapper.
   *
   * @return any template object
   * @param wrappableClass Class the interface
   * @param wrappableImpl the interface implementation
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <T> T wrap(Class<T> wrappableClass, T wrappableImpl) {
    return (T)Proxy.newProxyInstance(SyncProxyWrapper.class.getClassLoader(),
                                     new Class[] {wrappableClass},
                                     new SyncProxyWrapper<>(wrappableImpl));
  }
}
