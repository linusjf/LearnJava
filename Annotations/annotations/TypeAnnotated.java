package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
public @interface TypeAnnotated {
// empty class body
}
