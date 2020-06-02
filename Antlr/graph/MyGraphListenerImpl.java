package graph;

/** Listener used for walking through the parse tree. */
public class MyGraphListenerImpl extends GraphBaseListener {

  Graph g;

  public MyGraphListenerImpl(Graph g) {

    this.g = g;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void exitEdge(GraphParser.EdgeContext ctx) {
    // Once the edge rule is exited the data required for the edge i.e
    // vertices and the weight would be available in the EdgeContext
    // and the same can be used to populate the semantic model
    Vertex fromVertex = new Vertex(ctx.vertex(0).ID().getText());
    Vertex toVertex = new Vertex(ctx.vertex(1).ID().getText());
    double weight = Double.parseDouble(ctx.NUM().getText());
    Edge e = new Edge(fromVertex, toVertex, weight);
    g.addEdge(e);
  }
}
