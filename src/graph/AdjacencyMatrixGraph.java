import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
    // TODO: Implement this class

    private final List<List<Boolean>> connectionMatrix;
	private final List<Vertex> vertexList;

	public AdjacencyMatrixGraph() {
		connectionMatrix = new ArrayList<List<Boolean>>();
		vertexList = new ArrayList<Vertex>();
	}

	public void addVertex(Vertex v) {
		vertexList.add(v);
		//add false to whether there is an edge from the any other vertex to v
		for (int i = 0; i < connectionMatrix.size(); i++) {
			connectionMatrix.get(i).add(false);
		}
		//add the a new column with the connection from v to all the other vertices
		List<Boolean> column = new ArrayList<Boolean>();
		for (int i = 0; i < vertexList.size(); i++) {
			column.add(false);
		}
		connectionMatrix.add(column);
		System.out.println(vertexList);


	}

	public void addEdge(Vertex v1, Vertex v2) {
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
		column.set(vertexList.indexOf(v2), true);
	}

	public boolean edgeExists(Vertex v1, Vertex v2) {
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
		return column.get(vertexList.indexOf(v2));
	}

   

  }