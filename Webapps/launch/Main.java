package launch;

import java.text.MessageFormat;
import java.util.logging.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

@SuppressWarnings("PMD.ShortClassName")
public final class Main {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
  private static final String WORKING_DIR = System.getProperty("java.io.tmpdir") + "/webapps";

  private Main() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      Tomcat tomcat = new Tomcat();

      // The port that we should run on can be set into an environment variable
      // Look for that variable and default to 8080 if it isn't there.
      String webPort = System.getenv("PORT");
      if (webPort == null || webPort.isEmpty()) 
        webPort = "8080";
      System.out.println(webPort);
      tomcat.setSilent(false);
      tomcat.setPort(Integer.parseInt(webPort));
      tomcat.setBaseDir(WORKING_DIR);
      tomcat.getHost().setAppBase(WORKING_DIR);
      tomcat.getHost().setAutoDeploy(true);
      tomcat.getHost().setDeployOnStartup(true);

      System.out.println(System.getProperty("catalina.home"));
      System.out.println(System.getProperty("catalina.base"));

      String userDir = System.getProperty("user.dir");
      String webappDirLocation = userDir + "/dist/Webapps-2.0.0.war";

      Context appContext = tomcat.addWebapp(tomcat.getHost(), "/Webapp", webappDirLocation);
      appContext.setParentClassLoader(Thread.currentThread().getContextClassLoader());
      WebResourceRoot resources = new StandardRoot(appContext);
      resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", "", "/"));
      appContext.setResources(resources);

      // create default connector
      tomcat.getConnector();
      tomcat.start();
      LOGGER.info(() -> {
        return MessageFormat.format(
            "Deployed {0} as {1}", appContext.getBaseName(), appContext.getBaseName());
      });
      tomcat.getServer().await();
    } catch (LifecycleException lce) {
      System.err.println(lce);
    }
  }
}
