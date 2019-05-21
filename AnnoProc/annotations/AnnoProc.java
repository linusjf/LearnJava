package annotations;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Describe class <code>AnnoProc</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SupportedAnnotationTypes("SuppressWarnings")
public class AnnoProc extends AbstractProcessor {
  /**
   * Describe <code>process</code> method here.
   *
   * @param renv a <code>RoundEnvironment</code> value
   * @return a <code>boolean</code> value
   */
  public boolean process(Set<? extends TypeElement> elems, RoundEnvironment renv) {
    return true;
  }

  /**
   * Describe <code>getSupportedSourceVersion</code> method here.
   *
   * @return a <code>SourceVersion</code> value
   */
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }
}
