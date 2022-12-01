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
        // todo Auto-generated method stub

        AdjacencyListGraph twitterGraph = new AdjacencyListGraph();
        // Set<String> outputSet = new HashSet<String>();

        String dataBase = "datasets/twitter.txt";
        String commandFile = "datasets/commandQuery.txt";

        constructGraph(twitterGraph, dataBase);

        getQueries(twitterGraph, commandFile);

        // outputSet.add(queryResult);

        // String finalResult = String.join("", outputSet);

        outputQueries(queryResult);

    }

    // This method reads the file and construct a graph based on it
    private static void constructGraph(Graph graph, String file1) throws FileNotFoundException {

        File myFile = new File(file1);

        // Use scanner to tokenize the data
        try (Scanner data = new Scanner(myFile)) {

            Queue<Vertex> myQue = new LinkedList<>();

            while (data.hasNext()) {
                // ! Read the first line
                String str = data.next();

                // Assuming no error in the source file, we ignore every "->"
                if (!str.equals("->")) {
                    // ! Create a new vertex
                    Vertex thisVertex = new Vertex(str);
                    graph.addVertex(thisVertex);
                    myQue.add(thisVertex);
                }

                // Since we ignored "->", for every two vertices, we have an edge
                if (myQue.size() == 2) {
                    // ! Create a new edge
                    Vertex a = myQue.remove();
                    Vertex b = myQue.remove();

                    graph.addEdge(a, b);
                }

            }
        }
        // ! Close the scanner
    }

    // reads the commandQuery file calls the appropriate method
    private static void getQueries(Graph graph, String file2) throws InvalidAlgorithmParameterException {

        // ! Use scanner to tokenize the data

        StringBuilder sb = new StringBuilder(queryResult);

        try {

            BufferedReader brData = new BufferedReader(new FileReader(file2));
            String line;
            String[] commandArr;
            LinkedHashSet<String> mySet = new LinkedHashSet<String>();
            line = brData.readLine();

            // * */ Read the file line by line
            while ((line = brData.readLine()) != null) {

                mySet.add(line);

                if (line.contains("?")) {
                    commandArr = line.split(" ");

                    if (Objects.equals(commandArr[0], "commonInfluencers") && commandArr.length == 4) {
                        Vertex u1 = new Vertex(commandArr[1]);
                        Vertex u2 = new Vertex(commandArr[2]);

                        sb.append("query: ").append(commandArr[0]).append(" ").append(u1).append(" ").append(u2)
                                .append("\n");

                        for (Vertex users : Algorithms.commonDownstreamVertices(graph, u1, u2)) {
                            sb.append(users).append("\n");
                        }

                    } else if (Objects.equals(commandArr[0], "numRetweets") && commandArr.length == 4) {
                        Vertex u1 = new Vertex(commandArr[1]);
                        Vertex u2 = new Vertex(commandArr[2]);

                        sb.append("query: ").append(commandArr[0]).append(" ").append(u1).append(" ").append(u2)
                                .append("\n");

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
    // * */ This method writes the output to the output file

    private static void outputQueries(String result) {
        // ! Use scanner to tokenize the data
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datasets/output.txt"))) {

            bw.write(result);

        } catch (Exception e) {
            // * */ Test that an exception should be thrown
            System.out.println("Error: " + e);
        }
    }

}
