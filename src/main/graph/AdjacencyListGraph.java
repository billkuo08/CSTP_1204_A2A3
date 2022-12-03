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
		// initialize the adjacency list
		adjacencyList = new LinkedHashMap<Vertex, LinkedList<Vertex>>();
	}

	public void addVertex(Vertex v) {
		// add the vertex (key) and the value (addList) to the adjacency list
		LinkedList<Vertex> addList = new LinkedList<>();
		if (!adjacencyList.containsKey(v)) {
			adjacencyList.put(v, addList);
		}
	}

	public void addEdge(Vertex v1, Vertex v2) {
		// add the edge to the adjacency list
		LinkedList<Vertex> addList = adjacencyList.get(v1);
		if (!addList.contains(v1) && adjacencyList.containsKey(v2)) {
			addList.add(v2);
		}
		adjacencyList.put(v1, addList);
	}

	public boolean edgeExists(Vertex v1, Vertex v2) {
		// check if the edge exists in the adjacency list
		// Use get() to get the value mapped to the specified key
		// Use the contains method to check if the edge exists
		LinkedList<Vertex> existList = adjacencyList.get(v1);
		if (adjacencyList.containsKey(v1) && adjacencyList.containsKey(v2)) {
			return existList.contains(v2);
		}
		return false;
	}

	public List<Vertex> getVertices() {
		// return the list of vertices
		List<Vertex> result = new ArrayList<Vertex>();
		for (Vertex v : adjacencyList.keySet()) {
			result.add(v);
		}
		return result;
	}

	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		// return the list of downstream neighbors
		List<Vertex> result = new LinkedList<Vertex>();
		Queue<Vertex> myQueue = new LinkedList<Vertex>();

		for (Vertex x : adjacencyList.get(v)) {
			myQueue.add(x);
		}
		result.addAll(myQueue);
		return result;
	}

	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		// return the list of upstream neighbors
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		Queue<Vertex> myQueue = new LinkedList<Vertex>();
		// Use keySet() to get the set view of the keys contained in this map
		for (Vertex x : adjacencyList.keySet()) {
			// Use get() to get the value mapped to the specified key
			for (Vertex y : adjacencyList.get(x)) {
				// add the vertex to the queue if it is in the adjacency list
				if (y.equals(v)) {
					myQueue.add(x);
				}
			}
		}
		// add the queue to the result
		result.addAll(myQueue);

		return result;
	}

}
