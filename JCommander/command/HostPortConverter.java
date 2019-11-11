package command;

import com.beust.jcommander.IStringConverter;

public class HostPortConverter implements IStringConverter<HostPort> {
  @Override
  public HostPort convert(String value) {
    String[] s = value.split(":");
    return new HostPort(s[0], Integer.parseInt(s[1]));
  }
}
