import java.util.jar.Manifest
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

public void run() throws IOException
   {
      Manifest manifest = new Manifest(Files.newInputStream(Paths.get("manifest.mf")));
      JarOutputStream target = new JarOutputStream(new FileOutputStream("output.jar"), manifest);
      File inputDirectory = new File("inputDirectory");
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

