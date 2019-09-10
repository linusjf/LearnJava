package launch;

import java.io.File;
import java.nio.file.Paths;
import org.apache.catalina.Globals;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

  public static void main(String[] args) {
    try {
      String webappDirLocation = "./webapp";
      Tomcat tomcat = new Tomcat();

      // The port that we should run on can be set into an environment variable
      // Look for that variable and default to 8080 if it isn't there.
      String webPort = System.getenv("PORT");
      if (webPort == null || webPort.isEmpty()) {
        webPort = "8080";
      }

      tomcat.setPort(Integer.valueOf(webPort));

      StandardContext ctx = (StandardContext)tomcat.addWebapp(
          "./webapp", new File(webappDirLocation).getAbsolutePath());
      System.out.println(
          "configuring app with basedir: "
          + new File("./" + webappDirLocation).getAbsolutePath());

      ctx.setDefaultWebXml(Paths.get("./webapp/WEB-INF/web.xml").toString());
      // Declare an alternative location for your "WEB-INF/classes" dir
      // Servlet 3.0 annotation will work
      File additionWebInfClasses = new File("target/classes");
      WebResourceRoot resources = new StandardRoot(ctx);
      resources.addPreResources(
          new DirResourceSet(resources,
                             "/WEB-INF/classes",
                             additionWebInfClasses.getAbsolutePath(),
                             "/"));
      ctx.setResources(resources);

      System.out.println(System.getProperty("catalina.home"));
      System.out.println(System.getProperty("catalina.base"));
      tomcat.start();
      tomcat.getServer().await();
    } catch (LifecycleException lce) {
      System.err.println(lce);
    }
  }
}
