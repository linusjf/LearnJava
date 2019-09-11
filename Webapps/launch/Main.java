package launch;

import java.util.logging.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
  private static final String workingDir = System.getProperty("java.io.tmpdir");

  public static void main(String[] args) {
    try {

      String userDir = System.getProperty("user.dir");
      String webappDirLocation = userDir + "/dist/Webapps-2.0.0.war";
      Tomcat tomcat = new Tomcat();

      // The port that we should run on can be set into an environment variable
      // Look for that variable and default to 8080 if it isn't there.
      String webPort = System.getenv("PORT");
      if (webPort == null || webPort.isEmpty()) {
        webPort = "8080";
      }

      tomcat.setPort(Integer.valueOf(webPort));
      tomcat.setBaseDir(workingDir);
      tomcat.getHost().setAppBase(workingDir);
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

      // Alternatively, you can specify a WAR file as last parameter in the
      // following call e.g. "C:\\Users\\admin\\Desktop\\app.war"
      tomcat.getHost().getAppBaseFile().mkdir();

      Context appContext = tomcat.addWebapp("/app", webappDirLocation);
      LOGGER.info("Deployed " + appContext.getBaseName() + " as "
                  + appContext.getBaseName());
      tomcat.getServer().await();
    } catch (LifecycleException lce) {
      System.err.println(lce);
    }
  }
}
