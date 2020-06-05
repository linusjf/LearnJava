package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Graph {

  private final List<Edge> edges;
  private final Set<Vertex> vertices;

  public Graph() {
    edges = new ArrayList<>();
    vertices = new TreeSet<>();
  }

  void addEdge(Edge edge) {
    edges.add(edge);
    vertices.add(edge.getFromVertex());
    vertices.add(edge.getToVertex());
  }

  public void addVertice(Vertex v) {
    vertices.add(v);
  }

  public List<Edge> getEdges() {
    return edges;
  }

  public Set<Vertex> getVertices() {
    return vertices;
  }

  @SuppressWarnings("PMD.SystemPrintln")
  static void printGraph(Graph g) {
    System.out.println("Vertices...");
    for (Vertex v: g.getVertices())
      System.out.print(v.getLabel() + " ");
    System.out.println("");
    System.out.println("Edges...");
    for (Edge e: g.getEdges())
      System.out.println(e);
  }
}
