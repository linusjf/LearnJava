package container;

import java.util.Collection;

public interface PropertyContainer {
  /**
   * Add a property associated with a token name. If the token already exists, the value will be
   * replaced. If the token does not exist, it will be added with the value.
   *
   * @param value is an object that cannot be null
   * @param token is a key that can be used to retrieve the value
   */
  void addPropertyBy(Object value, String token);

  /**
   * Retrieve a value by a particular token.
   *
   * @param token is a key that can be used to retrieve the value
   * @return Object is the value associated with the token. It will not be null.
   */
  Object getPropertyBy(String token);

  /**
   * Retrieve all property keys currently in use.
   *
   * @return String[] is an array of all valid token names.
   */
  String[] getPropertyKeys();

  /*
   * Remove a value associated with a particular token.
   * @param token is a key associated with a value that was added
   */
  void removeProperty(String token);

  /*
   * Return collection of values in the properties object.
   */
  Collection<Object> values();
}
