package container;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class PropertyContainerImpl implements PropertyContainer, Serializable {
  public static final long serialVersionUID = 1L;

  protected Map<String, Object> ivProperties = new Hashtable<>();

  /**
   * Add a property associated with a token name. If the token already exists, the value will be
   * replaced. If the token does not exist, it will be added with the value.
   *
   * @param value is an object that cannot be null
   * @param token is a key that can be used to retrieve the value
   */
  @Override
  public void addPropertyBy(Object value, String token) {
    if (value == null || token == null) return;
    ivProperties.remove(token);
    ivProperties.put(token, value);
  }

  /**
   * Retrieve a value by a particular token.
   *
   * @param token is a key that can be used to retrieve the value
   * @return Object is the value associated with the token. It will not be null.
   */
  @Override
  public Object getPropertyBy(String token) {
    return token == null ? null : ivProperties.get(token);
  }

  /**
   * Retrieve all property keys currently in use.
   *
   * @return String[] is an array of all valid token names.
   */
  @Override
  public String[] getPropertyKeys() {
    String[] keys = null;
    synchronized (ivProperties) {
      Set<String> e = ivProperties.keySet();
      keys = e.toArray(new String[0]);
    }
    return keys;
  }

  /*
   * Remove a value associated with a particular token.
   * @param token is a key associated with a value that was added
   */
  @Override
  public void removeProperty(String token) {
    if (token == null) return;
    ivProperties.remove(token);
  }

  /*
   * Return collection of values in the properties object.
   */
  @Override
  public Collection<Object> values() {
    return ivProperties.values();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (o == this) return true;
    if (o instanceof PropertyContainerImpl)
      return ivProperties.equals(((PropertyContainerImpl) o).ivProperties);
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(ivProperties, super.hashCode());
  }
}
