package graph;

import java.util.Locale;
import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
  private String label;

  Vertex(String label) {
    this.label = label.toUpperCase(Locale.getDefault());
  }

  @Override
  public int compareTo(Vertex o) {
    return this.getLabel().compareTo(o.getLabel());
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Vertex)
      return compareTo((Vertex)o) == 0;
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(label);
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
