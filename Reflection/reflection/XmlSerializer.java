package reflection;

import com.zeta.util.Mopex;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;

public enum XmlSerializer {
  ;
  private static final String STRING_CLASS = "java.lang.String";

  public static Document serializeObject(Object source) throws IllegalAccessException {
    return serializeHelper(
        source, new Document(new Element("serialized")), new IdentityHashMap<Object, Object>());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static Element serializeVariable(
      Class<?> fieldtype, Object child, Document target, Map<Object, Object> table)
      throws IllegalAccessException {
    if (child == null) return new Element("empty");
    if (fieldtype.isPrimitive() || STRING_CLASS.equals(fieldtype.getName())) {
      Element value = new Element("value");
      value.setText(child.toString());
      return value;
    } else {
      Element reference = new Element("reference");
      if (table.containsKey(child)) reference.setText(table.get(child).toString());
      else {
        reference.setText(Integer.toString(table.size()));
        serializeHelper(child, target, table);
      }
      return reference;
    }
  }

  // clang-format off
  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  private static Document serializeHelper(Object source, Document target, Map<Object, Object> table)
      throws IllegalAccessException {
    String id = Integer.toString(table.size());
    table.put(source, id);
    Class<?> sourceclass = source.getClass();
    Element objElt = new Element("object");
    objElt.setAttribute("class", sourceclass.getName());
    objElt.setAttribute("id", id);
    if (sourceclass.isArray()) {
      int length = Array.getLength(source);
      objElt.setAttribute("length", Integer.toString(length));
      Class<?> componentType = sourceclass.getComponentType();
      for (int i = 0; i < length; i++)
        objElt.addContent(serializeVariable(componentType, Array.get(source, i), target, table));
    } else {
      Field[] fields = Mopex.getInstanceVariables(sourceclass);
      for (Field field : fields) {
        if (!Modifier.isPublic(field.getModifiers())) field.setAccessible(true);
        Element fldElt = new Element("field");
        fldElt.setAttribute("name", field.getName());
        Class<?> declClass = field.getDeclaringClass();
        fldElt.setAttribute("declaringclass", declClass.getName());

        Class<?> fieldtype = field.getType();
        fldElt.setAttribute("type", fieldtype.getName());
        Object child = field.get(source);
        fldElt.addContent(serializeVariable(fieldtype, child, target, table));
        objElt.addContent(fldElt);
      }
    }
    target.getRootElement().addContent(objElt);
    return target;
  }
  // clang-format on
}
