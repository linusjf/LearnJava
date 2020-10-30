import java.util.jar.Manifest
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import java.io.File
import java.io.BufferedInputStream
import java.util.Set
import java.io.FilenameFilter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

createJar()

public void createJar() throws IOException
   {
     def ver = properties.get("version")
       def buildDir = properties.get("build.dir")  
      JarOutputStream target = new JarOutputStream(Files.newOutputStream(Paths.get("dist/" + project.name + "-" + ver + ".jar")));
      File inputDirectory = new File("/tmp/jmhclasses");
      for (File nestedFile : inputDirectory.listFiles())
         add("", nestedFile, target);
      target.close();
   }

   private void add(String parents, File source, JarOutputStream target) throws IOException
   {
      BufferedInputStream in = null;
      try
      {
         String name = (parents + source.getName()).replace("\\", "/");

         if (source.isDirectory())
         {
            if (!name.isEmpty())
            {
               if (!name.endsWith("/"))
                  name += "/";
               JarEntry entry = new JarEntry(name);
               entry.setTime(source.lastModified());

               target.putNextEntry(entry);
               target.closeEntry();
            }
            for (File nestedFile : source.listFiles())
               add(name, nestedFile, target);
            return;
         }

         JarEntry entry = new JarEntry(name);
         entry.setTime(source.lastModified());
         target.putNextEntry(entry);
         in = new BufferedInputStream(new FileInputStream(source));

         byte[] buffer = new byte[1024];
         while (true)
         {
            int count = in.read(buffer);
            if (count == -1)
               break;
            target.write(buffer, 0, count);
         }
         target.closeEntry();
      }
      finally
      {
         if (in != null)
            in.close();
      }
   }

