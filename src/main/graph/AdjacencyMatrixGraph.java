package main.graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import main.staff.*;



public class AdjacencyMatrixGraph implements Graph {

  private final List<List<Boolean>> connectionMatrix;
  private final List<Vertex> vertexList;

  public AdjacencyMatrixGraph() {
    connectionMatrix = new ArrayList<List<Boolean>>();
    vertexList = new ArrayList<Vertex>();
  }

  public void addVertex(Vertex v) {
    vertexList.add(v);
    // * add false to whether there is an edge from the any other vertex to v
    for (int i = 0; i < connectionMatrix.size(); i++) {
      connectionMatrix.get(i).add(false);
    }
    // * add the a new column with the connection from v to all the other vertices
    List<Boolean> column = new ArrayList<Boolean>();
    for (int i = 0; i < vertexList.size(); i++) {
      column.add(false);
    }
    // * add the new column to the connection matrix
    connectionMatrix.add(column);
    System.out.println(vertexList);

  }

  // * add an edge from v1 to v2
  public void addEdge(Vertex v1, Vertex v2) {
    List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
    column.set(vertexList.indexOf(v2), true);
  }

  // * returns a list of vertices that are connected to v
  public boolean edgeExists(Vertex v1, Vertex v2) {
    List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
    return column.get(vertexList.indexOf(v2));
  }

  // * returns a list of vertices that are adjacent to v
  public List<Vertex> getVertices() {
    // * defensive copying to not give back the actual list of vertices
    return AdjacencyMatrixGraph.cloneList(vertexList);

  }

  // * helper method to clone a list
  private static List<Vertex> cloneList(List<Vertex> list) {
    List<Vertex> result = new ArrayList<Vertex>();
    if (list.size() == 0) {
      return result;
    }
    result.addAll(list);
    return result;
  }

  // * returns a list of vertices that are connected to the given vertex
  public List<Vertex> getDownstreamNeighbors(Vertex v) {
    LinkedList<Vertex> result = new LinkedList<Vertex>();
    List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v));

    // * iterate through the column and add the vertices that are connected to v
    for (int i = 0; i < column.size(); i++) {
      if (column.get(i)) {
        result.add(vertexList.get(i));
      }
    }
    return result;
  }

  // * returns a list of vertices that are connected to the given vertex
  public List<Vertex> getUpstreamNeighbors(Vertex v) {
    List<Vertex> result = new ArrayList<Vertex>();

    // * go through each column
    for (int i = 0; i < connectionMatrix.size(); i++) {
      List<Boolean> column = connectionMatrix.get(i);
      if (column.get(vertexList.indexOf(v))) {
        result.add(vertexList.get(i));
      }
    }

    // * defensive copying to not give back the actual list of vertices
    return result;
  }
  

}