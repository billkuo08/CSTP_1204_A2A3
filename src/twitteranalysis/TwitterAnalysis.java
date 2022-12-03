package twitteranalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

import main.graph.*;
import main.staff.*;

public class TwitterAnalysis {

    private static String queryResult = "";

    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException {
        //The graph itself
        AdjacencyListGraph twitterGraph = new AdjacencyListGraph();
        // Paths to both txt files
        String dataBase = "datasets/twitter.txt";
        String commandFile = "datasets/commandQuery.txt";

        constructGraph(twitterGraph, dataBase);

        getQueries(twitterGraph, commandFile);

        outputQueries(queryResult);

    }

    // This method reads the datasets and construct a graph based on it
    private static void constructGraph(Graph graph, String file1) throws FileNotFoundException {

        File myFile = new File(file1);

        // Use scanner to tokenize the data
        try (Scanner data = new Scanner(myFile)) {

            Queue<Vertex> myQue = new LinkedList<>();

            while (data.hasNext()) {
                // convert the next completed token into string
                String str = data.next();

                // Assuming no error in the source file, we ignore every "->"
                if (!str.equals("->")) {
                    // Create a vertex and add it to the graph
                    Vertex thisVertex = new Vertex(str);
                    graph.addVertex(thisVertex);
                    // Add the vertext to a queue
                    myQue.add(thisVertex);
                }

                // Since we ignored "->", for every two vertices, we have an edge
                if (myQue.size() == 2) {
                    // For every two vertices in the queue, we remove them and create an edge
                    Vertex a = myQue.remove();
                    Vertex b = myQue.remove();
                    // Add the edge to the graph
                    graph.addEdge(a, b);
                }

            }
        }

    }

    // Reads the commandQuery file and calls the according method
    private static void getQueries(Graph graph, String file2) throws InvalidAlgorithmParameterException {
        //Use stringbuilder to build the output string
        StringBuilder sb = new StringBuilder(queryResult);

        try {
            // Use a buffered reader to read the file
            BufferedReader brData = new BufferedReader(new FileReader(file2));
            String line;
            // Create a string array to store command queries
            String[] commandArr;
            // Use a LinkedHashSet to make sure there is no duplicate command line
            LinkedHashSet<String> mySet = new LinkedHashSet<String>();
            line = brData.readLine();

            // Read the file line by line, each line represents a command
            while ((line = brData.readLine()) != null) {

                mySet.add(line);

                if (line.contains("?")) {
                    commandArr = line.split(" ");
                    // If the command queury is "comminInflunecers", call the according method
                    if (Objects.equals(commandArr[0], "commonInfluencers") && commandArr.length == 4) {
                        //Create two new vetices based on the two userID provided in the command query file
                        Vertex u1 = new Vertex(commandArr[1]);
                        Vertex u2 = new Vertex(commandArr[2]);

                        // Display the command query
                        sb.append("query: ").append(commandArr[0]).append(" ").append(u1).append(" ").append(u2)
                        .append("\n");
                        //Applies the appropriate method(algorithm) to the graph with the new userID, afterward add all of them to the stringbuilder
                        for (Vertex users : Algorithms.commonDownstreamVertices(graph, u1, u2)) {
                            sb.append(users).append("\n");
                        }
                        // If the command queury is "numRetweets", call the according method
                    } else if (Objects.equals(commandArr[0], "numRetweets") && commandArr.length == 4) {
                        //Create two new vetices based on the two userID provided in the command query file
                        Vertex u1 = new Vertex(commandArr[1]);
                        Vertex u2 = new Vertex(commandArr[2]);

                        // Display the command query
                        sb.append("query: ").append(commandArr[0]).append(" ").append(u1).append(" ").append(u2)
                        .append("\n");
                        //Applies the appropriate method(algorithm) to the graph with the new userID, afterward add all of them to the stringbuilder
                        sb.append(Algorithms.shortestDistance(graph, u1, u2)).append("\n");

                    }
                }

            }

            queryResult = sb.toString();
            brData.close();

        } catch (Exception e) {
            // Test that an exception should be thrown
        }

    }
    //This method writes the output to output.txt

    private static void outputQueries(String result) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datasets/output.txt"))) {
            //Write the output created wtih the getQueries method to the pointed file of the path 
            bw.write(result);

        } catch (Exception e) {
            //Exception that should not be thrown
            System.out.println("Error: " + e);
        }
    }

}
