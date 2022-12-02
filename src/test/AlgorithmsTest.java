package test;

import static org.junit.Assert.*;
import org.junit.Test;
import main.graph.*;
import main.staff.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AlgorithmsTest {

    Graph graph;
    static Vertex a;
    static Vertex b;
    static Vertex c;

    static Vertex d;
    static Vertex e;
    static Vertex f;

    static Vertex g;
    static Vertex h;
    static Vertex i;

    static Vertex j;
    static Vertex k;
    static Vertex l;

    static int GRAPH_SIZE = 12;

    
    public AlgorithmsTest(Graph anInterface) {
        this.graph = anInterface;
    }

    @Before
    public void setup() throws InstantiationException, IllegalAccessException {
        this.graph = graph.getClass().newInstance();
        
        a = new Vertex("A");
        b = new Vertex("B");
        c = new Vertex("C");
        d = new Vertex("D");
        e = new Vertex("E");
        f = new Vertex("F");
        g = new Vertex("G");
        h = new Vertex("H");
        i = new Vertex("I");
        j = new Vertex("J");
        k = new Vertex("K");
        l = new Vertex("L");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);

        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);

        graph.addVertex(g);
        graph.addVertex(h);
        graph.addVertex(i);

        graph.addVertex(j);
        graph.addVertex(k);
        graph.addVertex(l);

        graph.addEdge(a, b);
        graph.addEdge(b, a);
        graph.addEdge(b, c);        
        graph.addEdge(b, d);
        graph.addEdge(c, a);
        graph.addEdge(d, c);
        graph.addEdge(b, e);
        graph.addEdge(b, f);
        graph.addEdge(e, b);
        graph.addEdge(f, b);
        graph.addEdge(d, g);
        graph.addEdge(g, h);
        graph.addEdge(g, i);
        graph.addEdge(i, j);
        graph.addEdge(i, k);

        
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[] { new AdjacencyListGraph() }, new Object[] { new AdjacencyMatrixGraph() });
    }

    private boolean checkDuplicate(List<Vertex> aList) {
        Set<Vertex> set1 = new HashSet<Vertex>();
        for (Vertex aVerterx : aList) {
            if (!set1.add(aVerterx)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testdfs() {
                  

        Set<List<Vertex>> setOfTraversalListsDFS = Algorithms.depthFirstSearch(graph);

        assertEquals(GRAPH_SIZE, setOfTraversalListsDFS.size());//size check

        // check the traversal(s)
        for (List<Vertex> aList : setOfTraversalListsDFS) {
            Vertex startingVertex = aList.get(0);

            // Check the traversal of Vertex b
            if (startingVertex.equals(b)) {   
                //check duplicates and size           
                assertEquals(GRAPH_SIZE - 1, aList.size()); 
                assertFalse(checkDuplicate(aList));
                // Check that A is after C
                assertEquals(1, Math.abs((aList.indexOf(c) - aList.indexOf(a))));
            }
        }
    }

    @Test
    public void testbfs() throws Exception {
        Set<List<Vertex>> setOfTraversalListsDFS = Algorithms.breadthFirstSearch(graph);

        assertEquals(GRAPH_SIZE, setOfTraversalListsDFS.size());//size check

        // check the traversal(s)
        for (List<Vertex> aList : setOfTraversalListsDFS) {
            Vertex startingVertex = aList.get(0);

            // Check the traversal of Vertex b
            if (startingVertex.equals(b)) {
                //check duplicates and size
                assertEquals(GRAPH_SIZE - 1, aList.size()); 
                assertFalse(checkDuplicate(aList));

                // Check that H and I are one distance apart
                assertTrue((Math.abs(aList.indexOf(h) - aList.indexOf(i))) == 1);
            }
        }
    }

    @Test
    public void shortestDistanceTest() {
        try {
            assertEquals(0, Algorithms.shortestDistance(graph, a, a));
            assertEquals(2, Algorithms.shortestDistance(graph, a, f));
            assertEquals(1, Algorithms.shortestDistance(graph, b, c));
            assertEquals(3, Algorithms.shortestDistance(graph, d, b));
            assertEquals(4, Algorithms.shortestDistance(graph, d, e));
            assertEquals(5, Algorithms.shortestDistance(graph, f, k));
        } catch (Exception e) {
            fail(); // Should not throw this exception
        }

        // Test no path, Exception or return special value
        try {
            assertTrue((Algorithms.shortestDistance(graph, j, k) < 0) || (Algorithms.shortestDistance(graph, j, k) >= GRAPH_SIZE));
        } catch (Exception e) {
            // Throw an exception if there is no path
        }
    }

    @Test
    public void commonDownstreamVerticesTest() {
        // Test Normal case
        List<Vertex> intersectionList = Algorithms.commonDownstreamVertices(graph, a, e);
        assertEquals(new HashSet<Vertex>() {{add(b);}}, new HashSet<Vertex>(intersectionList));
        System.out.println(intersectionList);

        // Test No commonUpstreamVertices
        assertEquals(0, Algorithms.commonDownstreamVertices(graph, l, a).size());
        
         // Test Symmetry
        assertEquals(Algorithms.commonDownstreamVertices(graph, a, e).size(), Algorithms.commonDownstreamVertices(graph, e, a).size());
    }

    @Test
    public void commonUpstreamVerticesTest() {

        // Test Normal case
        List<Vertex> intersectionList = Algorithms.commonUpstreamVertices(graph, d, e);

        assertEquals(new HashSet<Vertex>() {{add(b);}}, new HashSet<Vertex>(intersectionList));      
        
        // Test No commonUpstreamVertices
        assertEquals(0, Algorithms.commonUpstreamVertices(graph, l, a).size());                                                                      

        // Test Symmetry
        assertEquals(Algorithms.commonUpstreamVertices(graph, d, e).size(), Algorithms.commonUpstreamVertices(graph, e, d).size());
    }

   
    
}
