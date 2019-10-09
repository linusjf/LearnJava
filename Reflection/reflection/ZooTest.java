package reflection;

import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ZooTest {

  public static void main(String[] args) {

    Animal panda1 = new Animal("Tian Tian",               //$NON-NLS-1$
                               "male",                    //$NON-NLS-1$
                               "Ailuropoda melanoleuca",  //$NON-NLS-1$
                               271);
    Animal panda2 = new Animal("Mei Xiang",               //$NON-NLS-1$
                               "female",                  //$NON-NLS-1$
                               "Ailuropoda melanoleuca",  //$NON-NLS-1$
                               221);
    Zoo national = new Zoo("National Zoological Park",  //$NON-NLS-1$
                           "Washington, D.C.");         //$NON-NLS-1$

    national.add(panda1);
    national.add(panda2);

    try {
      XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());

      Document d = XmlSerializer.serializeObject(national);
      out.output(d, System.out);
    } catch (IllegalAccessException
        | IOException ex) {
      System.err.println(ex);
    }
  }
}
