package main.graph;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import main.staff.*;


public class AdjacencyListGraph implements Graph {

	
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

	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		List<Vertex> result = new LinkedList<Vertex>();
		Queue<Vertex> myQueue = new LinkedList<Vertex>();

		for (Vertex x : adjacencyList.get(v)) {
			myQueue.add(x);
		}
		result.addAll(myQueue);
		return result;
	}

	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		Queue<Vertex> myQueue = new LinkedList<Vertex>();

		for (Vertex x : adjacencyList.keySet()) {
			for (Vertex y : adjacencyList.get(x)) {
				if (y.equals(v)) {
					myQueue.add(x);
				}
			}
		}

		result.addAll(myQueue);

		return result;
	}

	

}
