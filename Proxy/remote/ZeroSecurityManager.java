package remote;

import java.security.Permission;

public class ZeroSecurityManager extends SecurityManager {
  @Override
  public void checkPermission(Permission permission) {
    //  System.out.println("checkPermission for : " + permission.toString());
  }
}
