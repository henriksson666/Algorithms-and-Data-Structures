/*******************************************************************************************
 * Universidade Estadual do Sudoeste da Bahia - UESB
 * Departamento de Ciências Exatas e Tecnológicas - DCET
 * Colegiado do Curso de Ciência da Computação - CCComp
 * Disciplina: Algoritmos e Estruturas de Dados
 * Professor: Profa. Me. Maria Luisa Ghizoni Gonzalez
 * 
 * Algorithm: Minimum Spanning Tree using Kruskal's and Prim's algorithms
 * 
 * Autores: João Henrique Silva Pinto (202210958), Cauê Rodrigues de Aguiar (202210181),
 *          Ademir de Jesus Reis Júnior (202210327)
 *******************************************************************************************/

/*******************************************************************************************
 * Explicações: A Árvore Geradora Mínima (AGM) é uma árvore que conecta todos os vértices de
 * um grafo ponderado com o menor custo possível. Existem dois algoritmos para encontrar a
 * AGM: Kruskal e Prim. O algoritmo de Kruskal utiliza a estrutura de dados Union-Find para
 * encontrar a AGM. O algoritmo de Prim utiliza uma fila de prioridade para encontrar a AGM.
 * As aplicações da AGM incluem: redes de computadores, redes de telefonia, redes de água,
 * redes de energia elétrica, etc.
 * 
 * Kruskal's algorithm: Neste algoritmo, os vértices são adicionados à AGM em ordem crescente
 * de seus pesos. O algoritmo começa com um conjunto de vértices isolados. Em cada iteração,
 * o algoritmo seleciona a aresta de menor peso e adiciona o vértice ao conjunto de vértices
 * da AGM, sem criar laços. O algoritmo para quando o número de arestas na AGM é igual ao
 * número de vértices menos 1.
 * 
 * Prim's algorithm: Neste algoritmo, os vértices são adicionados à AGM em ordem crescente de
 * seus pesos. O algoritmo começa com um vértice arbitrário. Em cada iteração, o algoritmo
 * seleciona a aresta de menor peso que tem um vértice na AGM e outro vértice fora da AGM e
 * adiciona o vértice ao conjunto de vértices da AGM, sem criar laços. O algoritmo para
 * quando o número de arestas na AGM é igual ao número de vértices menos 1. 
 *******************************************************************************************/

package mst; // Package declaration is mandatory

// Import packages
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Edge { // Class to represent an edge in the graph
    int src, dest, weight; // Source vertex, destination vertex, and weight of the edge

    /**
     * Edge constructor
     * @param src Source vertex
     * @param dest Destination vertex
     * @param weight Weight of the edge
     */
    public Edge(int src, int dest, int weight) { // Edge constructor
        this.src = src; // Initialize the source, destination, and weight of the edge
        this.dest = dest; // Initialize the source, destination, and weight of the edge
        this.weight = weight; // Initialize the source, destination, and weight of the edge
    } // End of Edge constructor
} // End of Edge class

class Graph { // Class to represent a graph
    int V, E; // Number of vertices and edges
    ArrayList<Edge> edges; // Array list of edges

    /**
     * Graph constructor
     * @param V Number of vertices
     * @param E Number of edges
     */
    public Graph(int V, int E) { // Graph constructor
        this.V = V; // Initialize the number of vertices
        this.E = E; // Initialize the number of edges
        edges = new ArrayList<>(E); // Initialize the array list of edges
    } // End of Graph constructor

    // Function to add an edge to the graph
    /**
     * Function to add an edge to the graph
     * @param src Source vertex
     * @param dest Destination vertex
     * @param weight Weight of the edge
     */
    void addEdge(int src, int dest, int weight) { // Add an edge to the graph
        edges.add(new Edge(src, dest, weight)); // Add an edge to the graph
    } // End of addEdge method

    // Kruskal's algorithm for Minimum Spanning Tree
    /**
     * Kruskal's algorithm for Minimum Spanning Tree
     */
    void kruskalMST() { // Kruskal's algorithm
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight)); // Sort the edges by weight in ascending order

        int[] parent = new int[V]; // Create an array of subsets
        for (int i = 0; i < V; i++) // Create V subsets with single elements
            parent[i] = i; // Create V subsets with single elements

        ArrayList<Edge> result = new ArrayList<>(); // Store the result

        int i = 0, e = 0; // Index of the next edge and the number of edges
        while (e < V - 1 && i < E) { // Loop until we find V-1 edges or we traverse all the edges
            Edge nextEdge = edges.get(i++); // Get the next edge
            int x = find(parent, nextEdge.src); // Find the set of the source vertex
            int y = find(parent, nextEdge.dest); // Find the set of the destination vertex

            if (x != y) { // If the source and destination vertices are not in the same set
                result.add(nextEdge); // Add the edge to the result
                union(parent, x, y); // Union the two sets
                e++; // Increment the number of edges
            } // End of if statement
        } // End of while loop

        // Print the result
        System.out.println("Kruskal's Minimum Spanning Tree:"); // Print the result
        for (Edge edge : result) { // Loop through the result
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight); // Print the result in the format of "src - dest : weight"
        } // End of loop
    } // End of kruskalMST method

    // Helper function to find the set of an element in Kruskal's algorithm
    /**
     * Find method to find the set of an element in Kruskal's algorithm
     * @param parent Array of parents
     * @param i Index of the element
     * @return 
     */
    int find(int[] parent, int i) { // Find method
        if (parent[i] != i) // If i is not the parent of itself
            parent[i] = find(parent, parent[i]); // Recursively find the parent of i
        return parent[i]; // Return the parent of i
    } // End of find method

    // Helper function to perform union of two sets in Kruskal's algorithm
    /**
     * Union method to perform union of two sets in Kruskal's algorithm
     * @param parent Array of parents
     * @param x 
     * @param y
     * @return 
     */
    void union(int[] parent, int x, int y) { // Union method
        int xRoot = find(parent, x); // Find the set of x
        int yRoot = find(parent, y); // Find the set of y
        parent[xRoot] = yRoot; // Make yRoot as parent of xRoot
    } // End of union method

    /**
     * Prim's algorithm for Minimum Spanning Tree
     * @param startVertex Starting vertex
     */
    void primMST(int startVertex) { // Prim's algorithm
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight)); // Create a min heap of edges
        boolean[] inMST = new boolean[V]; // Create an array to store the vertices in the MST
        ArrayList<Edge> result = new ArrayList<>(); // Store the result
        inMST[startVertex] = true; // Add the starting vertex to the MST

        while (result.size() < V - 1) { // Loop until we find V-1 edges
            for (Edge edge : edges) { // Loop through the edges
                if ((inMST[edge.src] && !inMST[edge.dest]) || (inMST[edge.dest] && !inMST[edge.src])) // If the edge connects a vertex in the MST and a vertex outside the MST
                    minHeap.add(edge); // Add the edge to the min heap
            } // End of loop

            Edge minEdge = minHeap.poll(); // Get the edge with the minimum weight

            if (minEdge == null) { // If minEdge is null
                System.out.println("Error: Graph is not connected."); // Print error message
                return; // Return to the caller
            } // End of if statement

            int newVertex = inMST[minEdge.src] ? minEdge.dest : minEdge.src; // Get the new vertex
            inMST[newVertex] = true; // Add the new vertex to the MST
            result.add(minEdge); // Add the edge to the result
        } // End of while loop

        // Print the result
        System.out.println("\nPrim's Minimum Spanning Tree:"); // Print the result
        for (Edge edge : result) // Loop through the result
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight); // Print the result in the format of "src - dest : weight"
    } // End of primMST method
} // End of Graph class

public class MinimumSpanningTree { // Main class
    /**
     * Main method
     * @param args Command line arguments
     */
    public static void main(String[] args) { // Main method
        int V = 5; // Number of vertices
        int E = 5; // Number of edges

        Graph graph = new Graph(V, E); // Create a graph object with V vertices and E edges

        // Add edges with weights to the graph
        graph.addEdge(0, 1, 3);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 2, 2);
        graph.addEdge(0, 3, 3);

        // Kruskal's Minimum Spanning Tree
        graph.kruskalMST(); // Print the result of Kruskal's algorithm
        int startVertex = 4;
        // Prim's Minimum Spanning Tree
        graph.primMST(startVertex); // Print the result of Prim's algorithm
    } // End of Main method
} // End of Main class