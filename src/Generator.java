/**
 * Public class Generator:
 * Generates data sets based on the given V and E values and writing them to the file
 */
import java.util.*;
import java.io.*;

public class Generator {

    /**
     * This class takes in the given V and E as parameters and returns generated data sets 
     * @param V
     * @param E
     */
    public static void generateData(int V, int E) {

        Random random = new Random();
        String[] nodes = new String[V];
        Set <String> edges = new HashSet<String>();

        // Generate nodes
        for (int v = 0; v < V; v++) {
            int vert = v + 1;
            nodes[v] = String.format("Node%03d", vert);
        }
        
        // Generate edges and write to file
        try (FileWriter filewriter = new FileWriter("dataset")) {
            for (int e = 0; e < E; e++) {
                String v1;
                String v2;
                //int weight = 10;
                int weight = random.nextInt(10) + 1;
                
                // Ensure no duplicate edges
                do {
                    v1 = nodes[random.nextInt(V)];
                    v2 = nodes[random.nextInt(V)];
                } 
                while (v1.equals(v2) || edges.contains(v1 + " " + v2));
                
                // Add edge to set
                edges.add(v1 + " " + v2);

                // Write edge to file
                filewriter.write(v1 + " " + v2 + " " + weight + "\n");
            }
        } 

        catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {}
}

