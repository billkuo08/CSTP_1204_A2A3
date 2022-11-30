package twitteranalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import main.graph.*;
import main.staff.*;

public class TwitterAnalysis {
    
    private static String stringResult = "";
    public static void main(String[ ] args) throws IOException, InvalidAlgorithmParameterException {

        AdjacencyListGraph twitterGraph = new AdjacencyListGraph();

        String fileLocation = "datasets\\twitter.txt"; 

        constructGraph(twitterGraph, fileLocation);

    }
    // This method reads the file and construct a graph based on it 
    private static void constructGraph(Graph graph, String fileName) throws FileNotFoundException{

        File myFile = new File(fileName);

        //Use scanner to tokenize the data
        try (Scanner data = new Scanner(myFile)){

            Queue<Vertex> myQue = new LinkedList<>();

            while (data.hasNext()){
                String str = data.next();
                
                //Assuming no error in the source file, we ignore every "->"
                if(!str.equals("->")){
                    Vertex thisVertex = new Vertex(str);
                    graph.addVertex(thisVertex);
                    myQue.add(thisVertex);
                }

                //Since we ignored "->", for every two vertices, we have an edge
                if(myQue.size() == 2){
                    Vertex a = myQue.remove();
                    Vertex b = myQue.remove();

                    graph.addEdge(a, b);
                }
            
            }
        }
        
    }

   

}
