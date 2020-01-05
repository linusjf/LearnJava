package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * Container for the {@link CanBeRepeated}. 
 * Annotation containing a list of values. 
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface RepeatedValues {
  CanBeRepeated[] value();
}
