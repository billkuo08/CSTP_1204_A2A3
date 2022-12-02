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
    public static void main(String[ ] args){

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
