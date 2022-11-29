package main.graph;

import main.staff.*;
import java.util.*;
import javax.naming.NameNotFoundException;

public class Algorithms {

	private static List<Vertex> dfsForVertex(Graph graph, Vertex start) {
		Stack<Vertex> queue = new Stack<Vertex>();
		List<Vertex> result = new ArrayList<Vertex>();
		queue.push(start);
		while (!queue.isEmpty()) {
			Vertex up = queue.pop();
			// Take the next element and add its downstream neighbors to the stack so that
			// they are the next ones on which to carry the search
			if (!result.contains(up)) {
				result.add(up);
				for (Vertex down : graph.getDownstreamNeighbors(up)) {
					queue.push(down);
				}
			}
		}
		return result;
	}

	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		Set<List<Vertex>> result = new LinkedHashSet<List<Vertex>>();
		for (Vertex vertex : graph.getVertices()) {
			// Do a DFS starting at each vertex of the graph
			result.add(Algorithms.dfsForVertex(graph, vertex));
		}
		return result;
	}

	private static Map<Vertex, Integer> bfsForVertex(Graph graph, Vertex start) {
		Map<Vertex, Integer> result = new LinkedHashMap<Vertex, Integer>();
		result.put(start, 0);
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(start);
		while (!queue.isEmpty()) {
			// Take the first element in the queue
			// Add all of its downstream neighbors to the queue to carry the BFS on them
			// after the vertices in the current level are done
			Vertex up = queue.remove();
			// Add all of its downstream neighbors to the map with a depth one bigger than
			// the depth of up
			for (Vertex down : graph.getDownstreamNeighbors(up)) {
				if (!result.containsKey(down)) {
					queue.add(down);
					result.put(down, result.get(up) + 1);
				}
			}
		}
		return result;
	}

	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
		// todo: Implement this method
		Set<List<Vertex>> result = new LinkedHashSet<List<Vertex>>();
		for (Vertex vertex : graph.getVertices()) {
			// Get a map with each vertex visited and the depth of it in the bfs starting
			// from vertex
			Map<Vertex, Integer> depths = Algorithms.bfsForVertex(graph, vertex);
			List<Vertex> visitedVertices = new ArrayList<Vertex>();
			// Add all the vertices visited into a list
			for (Map.Entry<Vertex, Integer> entry : depths.entrySet()) {
				visitedVertices.add(entry.getKey());
			}
			result.add(visitedVertices);
		}
		return result;
	}

	public static int shortestDistance(Graph graph, Vertex a, Vertex b) throws NameNotFoundException {
		// Do a BFS starting at vertex a and recording at what depth is each vertex
		Map<Vertex, Integer> distances = Algorithms.bfsForVertex(graph, a);
		if (!distances.containsKey(b)) {
			// Condition is only true if b was not visited by the BFS of a
			throw new NameNotFoundException();
		}

		return distances.get(b);
	}

	private static List<Vertex> commonVertices(List<Vertex> list1, List<Vertex> list2) {
		// loop through one list and check it each element of this list is contained in
		// the other one
		List<Vertex> result = new ArrayList<Vertex>();
		for (int i = 0; i < list1.size(); i++) {
			Vertex v = list1.get(i);
			if (list2.contains(v)) {
				result.add(v);
			}
		}
		return result;
	}
}