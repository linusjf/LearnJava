package command;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.IStringConverterFactory;

public class HostPortFactory implements IStringConverterFactory {
  @Override
  public Class<? extends IStringConverter<?>> getConverter(Class<?> forType) {
    if (forType.equals(HostPort.class))
      return HostPortConverter.class;
    else
      return null;
  }
}
