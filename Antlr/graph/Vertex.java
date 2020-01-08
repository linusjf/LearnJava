package graph;

import java.util.Locale;

public class Vertex implements Comparable<Vertex> {
  private String label;

  public Vertex(String label) {
    this.label = label.toUpperCase(Locale.getDefault());
  }

  @Override
  public int compareTo(Vertex o) {
    return this.getLabel().compareTo(o.getLabel());
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
