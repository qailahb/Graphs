package src;
/**
 * Public class Program:
 * Reads the generated data and sends it to Graph to be read and the dataset and csv files to be updated
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.FileWriter;


public class Program {
    /**
     * Main program 
     * Reads data produced by the generateData and runs it through Graph 
     * @param V
     * @param csvfile
     * @throws IOException
     */
    public static void readData(int V, FileWriter csvfile) throws IOException {

        int vstart = 0;
        int E = 0;
        String estart = "Node001";
    
        Graph g = new Graph();
        try (
            FileReader datafile = new FileReader("dataset");
            Scanner graphFile = new Scanner(datafile)) {
    
            // Read the edges and insert
            while (graphFile.hasNextLine()) {
                String line = graphFile.nextLine();
                E++;

                if (vstart == 0) {
                    estart = line.substring(0, 7); 
                    vstart++;
                }

                // Code taken from Graph main function
                StringTokenizer st = new StringTokenizer(line);
    
                String source = st.nextToken();
                String dest = st.nextToken();
                int cost = Integer.parseInt(st.nextToken());
                g.addEdge(source, dest, cost);
            }

        } catch (IOException e) {
            System.err.println("Error");
            return; // exit early if there's an error reading the file
        }
    
        //int V = g.getVerticesCount();
        g.dijkstra(estart, V, E, csvfile);
    }
    
    
    /**
     * Main class that passes in the required combinations of vertices and edges, passes them through generateData and readData
     * The printed out data is sent to a csv file, that can be easily input into excel to be viewed in a graphed format and analysed
     * @param args
     */
    public static void main( String [ ] args )
    {
        int[] vertices = {10, 20, 30, 40, 50};
        int[] edges = {20, 35, 50, 65, 80};
        
        try (FileWriter csvfile = new FileWriter("data.csv")) {
            for (int v : vertices) {
                for (int e : edges) {
                    Generator.generateData(v, e);
                    readData(v, csvfile);
                }    
            }
        }

        catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
