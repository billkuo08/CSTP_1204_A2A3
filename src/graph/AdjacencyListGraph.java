
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdjacencyListGraph implements Graph { 
  
  // TO JERICHO :must implement getUpStream and getDownStream

 // TODO: Implement this class
	/**
	 * Rep invariant: If a is a vertex of graph and there is an edge from a to b,
	 * then b is a vertex of the graph: If a vertex v is an element of any of the
	 * sets of the value set of adjacenyList, v is part of the key set of
	 * adjacencyList. There is no edge that goes from a vertex to itself: A vertex v
	 * cannot be contained by the set s, where s is the value of the entry whose key
	 * is v
	 * All vertex are not null *
	 * Abstraction function: this object represents a graph, and the edges that
	 * connect these vertices
	 */
	private final Map<Vertex, LinkedList<Vertex>> adjacencyList;

	public AdjacencyListGraph() {
		adjacencyList = new LinkedHashMap<Vertex, LinkedList<Vertex>>();
	}

	public void addVertex(Vertex v) {
		LinkedList<Vertex> addList = new LinkedList<>();
		if (!adjacencyList.containsKey(v)) {
			adjacencyList.put(v, addList);
		}
	}

	public void addEdge(Vertex v1, Vertex v2) {
		LinkedList<Vertex> addList = adjacencyList.get(v1);
		if (!addList.contains(v1) && adjacencyList.containsKey(v2)) {
			addList.add(v2);
		}
		adjacencyList.put(v1, addList);
	}

	public boolean edgeExists(Vertex v1, Vertex v2) {
		LinkedList<Vertex> existList = adjacencyList.get(v1);
		if (adjacencyList.containsKey(v1) && adjacencyList.containsKey(v2)) {
			return existList.contains(v2);
		}
		return false;
	}

	public List<Vertex> getVertices() {
		List<Vertex> result = new ArrayList<Vertex>();
		for (Vertex v : adjacencyList.keySet()) {
			result.add(v);
		}
		return result;
	}

  

}
