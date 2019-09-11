package launch;

import java.util.logging.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public final class Main {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
  private static final String WORKING_DIR =
      System.getProperty("java.io.tmpdir");

  private Main() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    try {

      Tomcat tomcat = new Tomcat();

      // The port that we should run on can be set into an environment variable
      // Look for that variable and default to 8080 if it isn't there.
      String webPort = System.getenv("PORT");
      if (webPort == null || webPort.isEmpty()) {
        webPort = "8080";
      }

      tomcat.setPort(Integer.valueOf(webPort));
      tomcat.setBaseDir(WORKING_DIR);
      tomcat.getHost().setAppBase(WORKING_DIR);
      tomcat.getHost().setAutoDeploy(true);
      tomcat.getHost().setDeployOnStartup(true);

      //      StandardContext ctx = (StandardContext)tomcat.addWebapp(
      //        "/Webapp", new File(webappDirLocation).getAbsolutePath());
      //  System.out.println("configuring app with basedir: "
      //                   + new File(webappDirLocation).getAbsolutePath());

      // 5ctx.setDefaultWebXml(Paths.get("./webapp/WEB-INF/web.xml").toString());
      // Declare an alternative location for your "WEB-INF/classes" dir
      // Servlet 3.0 annotation will work
      //      File additionWebInfClasses = new File("target/classes");
      //    WebResourceRoot resources = new StandardRoot(ctx);
      //  resources.addPreResources(
      //    new DirResourceSet(resources,
      //                     "/WEB-INF/classes",
      //                   additionWebInfClasses.getAbsolutePath(),
      //                 "/"));
      //  ctx.setResources(resources);

      System.out.println(System.getProperty("catalina.home"));
      System.out.println(System.getProperty("catalina.base"));
      tomcat.start();

      String userDir = System.getProperty("user.dir");
      String webappDirLocation = userDir + "/dist/Webapps-2.0.0.war";
      // Alternatively, you can specify a WAR file as last parameter in the
      // following call e.g. "C:\\Users\\admin\\Desktop\\app.war"
      tomcat.getHost().getAppBaseFile().mkdir();
      Context appContext = tomcat.addWebapp("/Webapp", webappDirLocation);
      LOGGER.info("Deployed " + appContext.getBaseName() + " as "
                  + appContext.getBaseName());
      tomcat.getServer().await();
    } catch (LifecycleException lce) {
      System.err.println(lce);
    }
  }
}
