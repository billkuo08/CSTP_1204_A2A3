
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GraphTest {
    @Test
    public void test1() {
        AdjacencyListGraph graph = new AdjacencyListGraph();

        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");
        Vertex v5 = new Vertex("E");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v1);
        graph.addEdge(v2, v3);
        graph.addEdge(v1, v4);
        graph.addEdge(v1, v5);

        System.out.println(graph.edgeExists(v1, v2));
        System.out.println(graph.edgeExists(v1, v3));
        
        System.out.println(graph.getDownstreamNeighbors(v1));
        System.out.println(graph.getUpstreamNeighbors(v2));

        System.out.println(graph.getVertices());

        assertEquals("[B, D, E]", graph.getDownstreamNeighbors(v1).toString());
        assertEquals("[C, A]", graph.getUpstreamNeighbors(v2).toString());

    }
}
